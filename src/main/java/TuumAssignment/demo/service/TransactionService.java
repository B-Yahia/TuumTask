package TuumAssignment.demo.service;

import TuumAssignment.demo.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Transaction getTransaction(Long transactionId);
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
