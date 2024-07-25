package com.example.rules.taxifare;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.*;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TaxiFareDMNConfiguration {

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(kieContainer.getKieBase()).get(DMNRuntime.class);
        String namespace = "https://kiegroup.org/dmn/_97604E61-7BA7-4A57-8532-A1F6DE9E71DD";
        String modelName = "demo-taxi-fare";
        DMNModel dmnModel = dmnRuntime.getModel(namespace, modelName);
        DMNContext dmnContext = dmnRuntime.newContext();
        for (Number distance : Arrays.asList(0.5,1,2)) {
            dmnContext.set("distance", distance);
            DMNResult dmnResult =
                    dmnRuntime.evaluateAll(dmnModel, dmnContext);
            for (DMNDecisionResult dr : dmnResult.getDecisionResults()) {
                System.out.println("Distance: " + distance + ", " +
                        "Decision: '" + dr.getDecisionName() + "', " +
                        "Result: " + dr.getResult());
            }
        }
        return kieContainer;
    }
}
