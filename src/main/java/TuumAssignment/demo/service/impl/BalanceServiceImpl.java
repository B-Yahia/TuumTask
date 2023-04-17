package TuumAssignment.demo.service.impl;

import TuumAssignment.demo.mapper.BalanceMapper;
import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceMapper balanceMapper;


    @Override
    public List<Balance> getBalancesByAccountId(Long accountId) {
        return balanceMapper.getBalances(accountId);
    }

    @Override
    public void updateBalance(Balance balance) {
         balanceMapper.updateBalance(balance);
    }
}
