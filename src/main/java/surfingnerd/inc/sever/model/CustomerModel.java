package surfingnerd.inc.sever.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer")
public class CustomerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customer_pk")
    @SequenceGenerator(name = "customer_pk", sequenceName = "customer_pk", allocationSize = 10, initialValue = 10000)
    private Long pk;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String hoursCounter;
    //doctor's referral
    @OneToMany(mappedBy = "customerPk")
    private List<DoctorReferralModel> doctorReferrals;
    //Address
    private String street;
    private String city;
    private Long employeePk;
}
