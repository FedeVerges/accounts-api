package unsl.repository;

import org.springframework.data.repository.query.Param;
import unsl.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRespository extends JpaRepository<Account, Long> {
    List<Account> findByHolder(@Param("holder") Long holder);
}
