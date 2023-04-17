package TuumAssignment.demo.service.impl;

import TuumAssignment.demo.exception.ResourceNotFoundException;
import TuumAssignment.demo.mapper.AccountMapper;
import TuumAssignment.demo.mapper.BalanceMapper;
import TuumAssignment.demo.model.Account;
import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.AccountService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    public Account createAccount(Account account) {
        accountMapper.createAccount(account);
        balanceMapper.createBalances(account.getAccountId(), account.getBalances());
        return account;
    }

    @Override
    public Account getAccount(Long accountId) {
        val account = accountMapper.getAccount(accountId);
        if (account == null) {
            throw new ResourceNotFoundException("Account not found with ID: " + accountId);
        }
        return account;
    }
}
