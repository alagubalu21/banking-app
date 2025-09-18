package com.bankingapp.transaction_service.service;

import com.bankingapp.transaction_service.entity.Transaction;
import com.bankingapp.transaction_service.entity.TransactionType;
import com.bankingapp.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository repository;


    public Transaction deposit(int userId, double amount) {
        double currentBalance = getBalance(userId);
        Transaction tx = new Transaction(userId, amount, currentBalance + amount,
                TransactionType.DEPOSIT, LocalDateTime.now());
        return repository.save(tx);
    }

    public Transaction credit(int userId, double amount) {
        double currentBalance = getBalance(userId);
        Transaction tx = new Transaction(userId, amount, currentBalance + amount,
                TransactionType.CREDIT, LocalDateTime.now());
        return repository.save(tx);
    }

    public Transaction debit(int userId, double amount) {
        double currentBalance = getBalance(userId);
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        Transaction tx = new Transaction(userId, amount, currentBalance - amount,
                TransactionType.DEBIT, LocalDateTime.now());
        return repository.save(tx);
    }

    public double getBalance(int userId) {
        List<Transaction> transactions = repository.findByUserIdOrderByTimestampDesc(userId);
        return transactions.isEmpty() ? 0.0 : transactions.get(0).getBalance();
    }

    public List<Transaction> getTransactions(int userId) {
        return repository.findByUserIdOrderByTimestampDesc(userId);
    }
}
