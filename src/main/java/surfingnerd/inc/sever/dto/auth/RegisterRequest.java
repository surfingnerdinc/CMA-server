package surfingnerd.inc.sever.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import surfingnerd.inc.sever.enums.RoleEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String password;
    private String firstName;
    private String lastName;
    private RoleEnum role;
}
