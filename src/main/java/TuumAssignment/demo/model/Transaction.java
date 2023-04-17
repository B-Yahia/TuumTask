package TuumAssignment.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class Transaction {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private Currency currency;
    private Direction direction;
    @NotNull(message = "Description missing")
    private String description;
    private BigDecimal balanceAfter;
}
