package TuumAssignment.demo.service;

import TuumAssignment.demo.model.Balance;

import java.util.List;

public interface BalanceService {
    List<Balance> getBalancesByAccountId(Long accountId);
    void updateBalance (Balance balance);
}
