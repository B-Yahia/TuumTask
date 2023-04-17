package TuumAssignment.demo.controller;

import TuumAssignment.demo.model.Transaction;
import TuumAssignment.demo.service.TransactionService;
import TuumAssignment.demo.service.impl.TransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transaction/{transactionId}")
    public Transaction getTransaction(@PathVariable("transactionId") Long transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @GetMapping("/transactions/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable("accountId") Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
