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
import java.util.Optional;

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

    private Balance getAccountBalanceWithRightCurrency(Currency currency, List<Balance> balanceList) {
        Optional<Balance> draftBalance = balanceList.stream().filter(b -> b.getCurrency() == currency).findFirst();
        return draftBalance.orElseThrow(() -> new TransactionRelatedException("Invalid currency"));
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
