package com.example.rules.taxifare;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.api.runtime.KieSession;
import org.kie.dmn.api.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class TaxiFareCalculator {

    @Autowired
    private KieContainer kieContainer;

    public Double calculateFareWithDMN(TaxiRide taxiRide) {
        Fare rideFare = new Fare();
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(kieContainer.getKieBase()).get(DMNRuntime.class);
        String namespace = "https://kiegroup.org/dmn/_97604E61-7BA7-4A57-8532-A1F6DE9E71DD";
        String modelName = "demo-taxi-fare";
        DMNModel dmnModel = dmnRuntime.getModel(namespace, modelName);
        DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("distance", taxiRide.getDistanceKm());
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
            System.out.println("Distance: " + taxiRide.getDistanceKm() + ", " +
                    "Decision: '" + dr.getDecisionName() + "', " +
                    "Result: " + dr.getResult());
            rideFare.setRideFare(((BigDecimal)dr.getResult()).doubleValue());
        }
        return rideFare.getRideFare();
    }

    public Double calculateFare(TaxiRide taxiRide, Fare rideFare) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("fare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rideFare.getRideFare();
    }

}
