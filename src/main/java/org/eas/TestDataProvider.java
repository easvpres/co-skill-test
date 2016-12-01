package org.eas;

import org.eas.model.OrderStatus;
import org.eas.model.Product;
import org.eas.model.User;
import org.eas.model.UserOrder;
import org.eas.repository.OrderRepository;
import org.eas.repository.ProductRepository;
import org.eas.repository.UserRepository;
import org.eas.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author eas
 */
@Component
public class TestDataProvider {

    private static final Logger logger = LoggerFactory.getLogger(TestDataProvider.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void fillData() {
        logger.info("fill test data");
//        Product p1 = new Product(null, "a1", 1.0);
//        Product p2 = new Product(null, "a2", 2.0);
//        Product p3 = new Product(null, "a3", 5.0);
//        Product p4 = new Product(null, "a4", 10.0);
//        Product p5 = new Product(null, "a5", 20.0);
//        Product p6 = new Product(null, "a6", 50.0);
//        Product p7 = new Product(null, "a7", 100.0);
//
//        productRepository.save(p1);
//        productRepository.save(p2);
//        productRepository.save(p3);
//        productRepository.save(p4);
//        productRepository.save(p5);
//        productRepository.save(p6);
//        productRepository.save(p7);
//
//        UserOrder o1 = new UserOrder(null, "n1", LocalDateTime.now(), OrderStatus.NEW, Arrays.asList(p1, p2, p3));
//        orderRepository.save(o1);
//
//        userRepository.save(new User(null, "n1", 10.0, new ArrayList<>()));
//        userRepository.save(new User(null, "n2", 20.0, new ArrayList<>()));
//        userRepository.save(new User(null, "n3", 30.0, new ArrayList<>()));
    }
}
