package surfingnerd.inc.sever.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surfingnerd.inc.sever.model.EmployeeModel;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeModel, Long> {

    Optional<EmployeeModel> findByUsername(String username);

}
