package TuumAssignment.demo.controller;

import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.BalanceService;
import TuumAssignment.demo.service.impl.BalanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts/{accountId}/balances")
public class BalanceController {

    @Autowired
    private BalanceServiceImpl balanceService;

    @GetMapping
    public List<Balance> getBalancesByAccountId(@PathVariable("accountId") Long accountId) {
        return balanceService.getBalancesByAccountId(accountId);
    }
}
