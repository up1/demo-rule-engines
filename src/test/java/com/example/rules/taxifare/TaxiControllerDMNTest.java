package com.example.rules.taxifare;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaxiControllerDMNTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("ระยะทางน้อยกว่า 1 กิโลเมตร ค่า taxi เท่ากับ 40 บาท")
    void case01() {
        // Act
        ResultResponse result = testRestTemplate.getForObject("/taxi2/0.5", ResultResponse.class);
        // Assert
        assertEquals(0.5, result.getDistance());
        assertEquals(40.0, result.getTotalFare());
    }

    @Test
    @DisplayName("ระยะทางเท่ากับ 1 กิโลเมตร ค่า taxi เท่ากับ 40 บาท")
    void case02() {
        // Act
        ResultResponse result = testRestTemplate.getForObject("/taxi2/1.0", ResultResponse.class);
        // Assert
        assertEquals(1.0, result.getDistance());
        assertEquals(40.0, result.getTotalFare());
    }

    @Test
    @DisplayName("ระยะทางเกิน 1 กิโลเมตร แต่ไม่เกิน 10 กิโลเมตร จะคิดราคากิโลเมตรละ 6.50 บาท")
    void case03() {
        // Act
        ResultResponse result = testRestTemplate.getForObject("/taxi2/2.0", ResultResponse.class);
        // Assert
        assertEquals(2.0, result.getDistance());
        assertEquals(40.0 + (1 * 6.50), result.getTotalFare());
    }
}