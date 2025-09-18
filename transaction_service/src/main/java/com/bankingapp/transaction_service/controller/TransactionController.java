package com.bankingapp.transaction_service.controller;

import com.bankingapp.transaction_service.entity.Transaction;
import com.bankingapp.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/deposit/{userId}")
    public Transaction deposit(@PathVariable int userId, @RequestParam double amount) {
        return service.deposit(userId, amount);
    }

    @PostMapping("/credit/{userId}")
    public Transaction credit(@PathVariable int userId, @RequestParam double amount) {
        return service.credit(userId, amount);
    }

    @PostMapping("/debit/{userId}")
    public Transaction debit(@PathVariable int userId, @RequestParam double amount) {
        return service.debit(userId, amount);
    }

    @GetMapping("/balance/{userId}")
    public double getBalance(@PathVariable int userId) {
        return service.getBalance(userId);
    }

    @GetMapping("/history/{userId}")
    public List<Transaction> getHistory(@PathVariable int userId) {
        return service.getTransactions(userId);
    }
}
