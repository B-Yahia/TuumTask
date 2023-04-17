package TuumAssignment.demo.service;

import TuumAssignment.demo.model.Account;
import TuumAssignment.demo.model.Balance;

import java.util.List;

public interface AccountService {
    Account createAccount(Long customerId, String country, List<Balance> balances);
    Account getAccount(Long accountId);
}
