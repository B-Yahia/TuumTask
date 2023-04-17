package TuumAssignment.demo.service.impl;

import TuumAssignment.demo.exception.TransactionRelatedException;
import TuumAssignment.demo.mapper.TransactionMapper;
import TuumAssignment.demo.model.*;
import TuumAssignment.demo.service.TransactionService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private BalanceServiceImpl balanceService;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        val accountId = transaction.getAccountId();
        val account = accountService.getAccount(accountId);
        boolean validCurrency = false;
        validCurrency = isValidCurrency(transaction, account, validCurrency);
        if (validCurrency==false){
            throw new TransactionRelatedException("Invalid currency");
        }
        if (transaction.getAmount().compareTo(BigDecimal.ZERO)<=0){
            throw new TransactionRelatedException("Invalid Amount");
        }
        val balance = getAccountBalanceWithRightCurrency(transaction.getCurrency(),account.getBalances());
        changeBalanceAmountAndSaveChange(transaction, balance);
        transaction.setBalanceAfter(balance.getAvailableAmount());
        transactionMapper.createTransaction(transaction);
        return transaction;
    }

    private void changeBalanceAmountAndSaveChange(Transaction transaction, Balance balance) {
        if (transaction.getDirection() == Direction.OUT ){
            if (transaction.getAmount().compareTo(balance.getAvailableAmount())==1){
                throw new TransactionRelatedException("Insufficient funds");
            }else {
                balance.setAvailableAmount(balance.getAvailableAmount().subtract(transaction.getAmount()));
            }
        }else{
            balance.setAvailableAmount(balance.getAvailableAmount().add(transaction.getAmount()));
        }
        balanceService.updateBalance(balance);
    }

    private Balance getAccountBalanceWithRightCurrency(Currency currency ,List<Balance> balanceList){
        Balance draftBalance = new Balance();
        for (Balance balance: balanceList) {
            if (currency==balance.getCurrency()){
                draftBalance = balance;
            }
        }
        return draftBalance;
    }
    private boolean isValidCurrency(Transaction transaction, Account account, boolean validCurrency) {
        for (Balance balance: account.getBalances()) {
            if (transaction.getCurrency()==balance.getCurrency()){
                validCurrency =true;
            }
        }
        return validCurrency;
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionMapper.getTransaction(transactionId);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionMapper.getTransactionsByAccountId(accountId);
    }
}
