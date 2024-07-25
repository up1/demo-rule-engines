package com.example.rules.promotions;

import com.example.rules.taxifare.ResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PromotionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void calculate() {
        // Act
        String result = testRestTemplate.getForObject("/discount", String.class);
        // Assert
        assertEquals("100.0", result);
    }
}