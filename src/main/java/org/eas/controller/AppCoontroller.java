package org.eas.controller;

import org.eas.model.UserOrder;
import org.eas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author eas
 */
@RestController
@RequestMapping(path = "/order")
public class AppCoontroller {

    @Autowired
    private OrderService orderService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    UserOrder get(@RequestParam String orderNumber) {
//        Optional<UserOrder> orderOptional = orderService.find(orderNumber);
////        if (orderOptional.isPresent()) {
//            return orderOptional.get();
////        }
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Callable<UserOrder> get(@RequestParam String orderNumber) {
        return () -> orderService.find(orderNumber).get();
    }

    @PostMapping()
    @ResponseBody
    Callable<String> create(@RequestParam String userNumber, @RequestParam List<String> productArticles) {
        return () -> orderService.create(userNumber, productArticles).getNumber();
    }

//    @GetMapping(path = "test")
//    @Async
//    void get() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
////        Thread.sleep(5000);
//        throw new RuntimeException("123");
//    }

    @GetMapping(path = "test")
//    @Async
    @ResponseBody
    Callable<String> get2() throws InterruptedException {
        System.out.println(321);
        return () -> {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            throw new RuntimeException("123");
//            return "123";
        };
    }

}
