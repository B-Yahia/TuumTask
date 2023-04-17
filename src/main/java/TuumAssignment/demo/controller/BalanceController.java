package TuumAssignment.demo.controller;
import TuumAssignment.demo.model.Balance;
import TuumAssignment.demo.service.impl.BalanceServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balances/{accountId}")
public class BalanceController {

    @Autowired
    private BalanceServiceImpl balanceService;

    @GetMapping
    public ResponseEntity<List<Balance>> getBalancesByAccountId(@PathVariable("accountId") Long accountId) {
        val balanceList =balanceService.getBalancesByAccountId(accountId);
        return new ResponseEntity<>(balanceList, HttpStatus.OK);
    }
}
