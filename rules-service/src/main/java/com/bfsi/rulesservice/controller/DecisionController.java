package com.bfsi.rulesservice.controller;

import com.bfsi.rulesservice.dto.TransactionRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DecisionController {

    private final KieContainer kieContainer;

    public DecisionController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @PostMapping("/transaction-fraud")
    private TransactionRequest checkTransactionFraud(@RequestBody TransactionRequest transactionRequest) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(transactionRequest);
        kieSession.fireAllRules();
        kieSession.dispose();
        return transactionRequest;
    }
}
