package com.example.rules.promotions;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionController {

    @Autowired
    private KieContainer kieContainer;

    @GetMapping("/discount")
    public String calculate() {
        Order order = new Order();
        order.addProduct(new Product(1, "name01", "Electronics", "BrandA"));

        KieSession kieSession = kieContainer.newKieSession("rulesSession");
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(order.getDiscount());
        return "" + order.getDiscount();
    }

}
