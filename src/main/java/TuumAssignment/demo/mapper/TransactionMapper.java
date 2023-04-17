package TuumAssignment.demo.mapper;

import TuumAssignment.demo.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionMapper {
    void createTransaction(Transaction transaction);
    Transaction getTransaction(@Param("transactionId") Long transactionId);
    List<Transaction> getTransactionsByAccountId(@Param("accountId") Long accountId);

}
