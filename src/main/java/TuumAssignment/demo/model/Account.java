package TuumAssignment.demo.model;

import lombok.Data;

import java.util.List;
@Data
public class Account {
    private Long accountId;
    private Long customerId;
    private String country;
    private List<Balance> balances;
}
