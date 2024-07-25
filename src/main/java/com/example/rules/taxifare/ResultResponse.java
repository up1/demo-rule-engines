package com.example.rules.taxifare;

public class ResultResponse {
    private final Double distance;
    private final Double totalFare;


    public ResultResponse(Double distance, Double totalFare) {
        this.distance = distance;
        this.totalFare = totalFare;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getTotalFare() {
        return totalFare;
    }
}
