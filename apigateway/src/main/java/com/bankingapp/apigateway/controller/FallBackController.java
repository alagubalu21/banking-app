package com.bankingapp.apigateway.controller;

import com.bankingapp.apigateway.constants.CommonConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class FallBackController {

    @GetMapping("/authfallback")
    public ResponseEntity<Object> productFallBack() {
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap(CommonConstants.Response, CommonConstants.AuthServiceDown));
    }

    @GetMapping("/transactionfallback")
    public ResponseEntity<Object> searchFallBack() {
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap(CommonConstants.Response, CommonConstants.TransactionServiceDown));
    }

    @GetMapping("/statementfallback")
    public ResponseEntity<Object> pricingFallBack() {
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap(CommonConstants.Response, CommonConstants.StatementServiceDown));
    }
}
