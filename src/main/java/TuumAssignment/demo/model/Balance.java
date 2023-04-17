package TuumAssignment.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Balance {
    private Long id;
    private BigDecimal availableAmount;
    @NotNull(message = "Invalid currency")
    private Currency currency;
}
