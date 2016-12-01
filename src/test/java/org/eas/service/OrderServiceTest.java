package org.eas.service;

import org.eas.Application;
import org.eas.TestDataProvider;
import org.eas.model.Product;
import org.eas.model.User;
import org.eas.repository.ProductRepository;
import org.eas.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author eas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(value = TestDataProvider.class))
public class OrderServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    private Random random = new Random();

    @Test
    public void create() throws Exception {
        User user = new User(1L, "n1", 10.0);
        userRepository.save(user);

        Product p1 = new Product(null, "a1", 1.0);
        Product p2 = new Product(null, "a2", 2.0);
        Product p3 = new Product(null, "a3", 5.0);
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        orderService.create("n1", Arrays.asList("a1", "a2", "a3"));

        User user1 = userRepository.findOne(1L);
        Assert.assertEquals(Double.valueOf(2.0), user1.getBalance());
    }

//    @Test
//    public void create() throws Exception {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            users.add(new User(null, "n" + i, (double)random.nextInt(50), Collections.emptyList()));
//        }
//        userRepository.save(users);
//
//        orderService.create("n1", )
//    }
}