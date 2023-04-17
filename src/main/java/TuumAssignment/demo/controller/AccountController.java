package TuumAssignment.demo.controller;

import TuumAssignment.demo.model.Account;
import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.AccountService;
import TuumAssignment.demo.service.impl.AccountServiceImpl;
import jakarta.validation.Valid;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody  Account account) {
        val draftAccount = accountService.createAccount(account);
        return new ResponseEntity<>(draftAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountId") Long accountId) {
        val draftAccount = accountService.getAccount(accountId);
        return new ResponseEntity<>(draftAccount, HttpStatus.OK);
    }
}
