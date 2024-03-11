package surfingnerd.inc.sever.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "doctor_referral")
public class DoctorReferralModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "referral_pk")
    @SequenceGenerator(name = "referral_pk", sequenceName = "referral_pk", allocationSize = 1)
    private Long pk;
    private String isDoctorReferral;
    private String referralCode;
    private String icd10;
    private String icd10Description;
    private String assignedHours;
    private String usedHours;
    private boolean isFinished;
    private Date referralDate;
    private Date validUntil;
    private Long customerPk;
}
