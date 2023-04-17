package TuumAssignment.demo.mapper;
import TuumAssignment.demo.model.Balance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BalanceMapper {
    void createBalances(@Param("accountId") Long accountId, @Param("balances") List<Balance> balances);
    List<Balance> getBalances(@Param("accountId") Long accountId);
    void updateBalance (Balance balance);
}
