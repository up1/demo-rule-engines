package com.example.rules.taxifare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxiController {


    private final TaxiFareCalculator taxiFareCalculator;

    public TaxiController(TaxiFareCalculator taxiFareCalculator) {
        this.taxiFareCalculator = taxiFareCalculator;
    }

    @GetMapping("/taxi2/{distance}")
    public ResultResponse calculateDMN(@PathVariable Double distance) {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setDistanceKm(distance);
        Double totalFare = taxiFareCalculator.calculateFareWithDMN(taxiRide);

        ResultResponse resultResponse = new ResultResponse(distance, totalFare);
        return resultResponse;
    }

    @GetMapping("/taxi/{distance}")
    public ResultResponse calculate(@PathVariable Double distance) {
        TaxiRide taxiRide = new TaxiRide();
        taxiRide.setDistanceKm(distance);
        Fare fare = new Fare();
        Double totalFare = taxiFareCalculator.calculateFare(taxiRide, fare);

        ResultResponse resultResponse = new ResultResponse(distance, totalFare);
        return resultResponse;
    }

}
