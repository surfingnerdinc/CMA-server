package surfingnerd.inc.sever.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surfingnerd.inc.sever.model.DoctorReferralModel;

@Repository
public interface DoctorReferralDAO extends JpaRepository<DoctorReferralModel, Long> {
}
