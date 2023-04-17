package TuumAssignment.demo.controller;

import TuumAssignment.demo.model.Transaction;
import TuumAssignment.demo.service.TransactionService;
import TuumAssignment.demo.service.impl.TransactionServiceImpl;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        val draftTransaction =transactionService.createTransaction(transaction);
        return new ResponseEntity<>(draftTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") Long transactionId) {
        val draftTransaction = transactionService.getTransaction(transactionId);
        return new ResponseEntity<>(draftTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/byAccountId/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable("accountId") Long accountId) {
        val transactionList = transactionService.getTransactionsByAccountId(accountId);
        return new ResponseEntity<>(transactionList, HttpStatus.CREATED);
    }

}
