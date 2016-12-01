package org.eas.service;

import org.eas.exception.NoSuchUserException;
import org.eas.exception.NotEnoughMoneyException;
import org.eas.model.UserOrder;
import org.eas.model.OrderStatus;
import org.eas.model.Product;
import org.eas.model.User;
import org.eas.repository.OrderRepository;
import org.eas.repository.ProductRepository;
import org.eas.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author eas
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserOrder> find(String orderNumber) {
        return orderRepository.findOneByNumber(orderNumber);
    }

    private ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    @Transactional
    private UserOrder create2(List<String> productArticles, String userNumber) throws NoSuchUserException, NotEnoughMoneyException {
        Optional<User> userOptional = userRepository.findByNumber(userNumber);
        if (!userOptional.isPresent()) {
            throw new NoSuchUserException();
        }
        User user = userOptional.get();
        List<Product> products = productRepository.findByArticleIn(productArticles);

        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
        double newBalance = user.getBalance() - totalPrice;
        if (newBalance < 0) {
            throw new NotEnoughMoneyException(userNumber);
        }
        user.setBalance(newBalance);
        userRepository.save(user);

        UserOrder order = new UserOrder();
        order.setNumber(UUID.randomUUID().toString().substring(0, 20));
        order.setStatus(OrderStatus.NEW);
        order.setTimestamp(LocalDateTime.now());
        order.setProducts(products);
        order.setUser(user);
        orderRepository.save(order);

        return order;
    }

    public UserOrder create(String userNumber, List<String> productArticles) throws NoSuchUserException, NotEnoughMoneyException {
        logger.info("create: userNumber={}, productArticles={}", userNumber, Arrays.toString(productArticles.toArray()));
        String value = map.putIfAbsent(userNumber, userNumber);
        if (value != null) {
            logger.info("user '{}' is processing", userNumber);
            return null;
        }

        UserOrder userOrder = create2(productArticles, userNumber);

        map.remove(userNumber);
        return userOrder;
    }
}
