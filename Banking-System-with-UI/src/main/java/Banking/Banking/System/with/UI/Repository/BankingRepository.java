package Banking.Banking.System.with.UI.Repository;

import Banking.Banking.System.with.UI.Entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingRepository extends JpaRepository<BankEntity, Long>{
}
