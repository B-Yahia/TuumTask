package TuumAssignment.demo.mapper;

import TuumAssignment.demo.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    void createAccount(Account account);
    Account getAccount(@Param("accountId") Long id);
}
