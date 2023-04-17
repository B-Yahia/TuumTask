package TuumAssignment.demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {
    private Long id;
    private BigDecimal availableAmount;
    private Currency currency;
}
