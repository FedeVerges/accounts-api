package unsl.repository;

import unsl.entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRespository extends JpaRepository<Accounts, Long> {

}
