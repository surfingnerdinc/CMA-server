package surfingnerd.inc.sever.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surfingnerd.inc.sever.model.CustomerModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerModel, Long> {

    Optional<CustomerModel> findByFirstNameAndLastName(String firstName, String lastName);
    List<CustomerModel> findAllByEmployeePk(Long employeePk);
}
