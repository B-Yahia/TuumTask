package TuumAssignment.demo.controller;

import TuumAssignment.demo.model.Account;
import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.AccountService;
import TuumAssignment.demo.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping
    public Account createAccount(@RequestParam("customerId") Long customerId,
                                 @RequestParam("country") String country,
                                 @RequestBody List<Balance> balances) {
        return accountService.createAccount(customerId, country, balances);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable("accountId") Long accountId) {
        return accountService.getAccount(accountId);
    }
}
