package surfingnerd.inc.sever.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import surfingnerd.inc.sever.auth.service.AuthenticationService;
import surfingnerd.inc.sever.auth.service.JwtService;
import surfingnerd.inc.sever.dto.auth.AuthenticationRequest;
import surfingnerd.inc.sever.dto.auth.AuthenticationResponse;
import surfingnerd.inc.sever.dto.auth.RegisterRequest;
import surfingnerd.inc.sever.enums.RoleEnum;
import surfingnerd.inc.sever.exceptions.AuthenticateException;
import surfingnerd.inc.sever.exceptions.RegisterException;
import surfingnerd.inc.sever.model.EmployeeModel;
import surfingnerd.inc.sever.dao.EmployeeDAO;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    private final EmployeeDAO employeeDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws RegisterException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getPassword() == null) {
            throw new RegisterException("/api/v1/auth/register", HttpStatus.BAD_REQUEST, "Invalid request", null,
                    LocalDateTime.now().toString());
        }

        Optional<EmployeeModel> byUsername = employeeDAO.findByUsername(generateUserName(request.getFirstName(), request.getLastName()));
        if (byUsername.isPresent()) {
            throw new RegisterException("/api/v1/auth/register", HttpStatus.BAD_REQUEST, "Username already exists",
                    null, LocalDateTime.now().toString());
        }

        try {
            String username = generateUserName(request.getFirstName(), request.getLastName());
            RoleEnum role = getRoleEnum(request);

            var user = EmployeeModel.builder()
                                    .firstName(request.getFirstName())
                                    .lastName(request.getLastName())
                                    .username(username)
                                    .password(passwordEncoder.encode(request.getPassword()))
                                    .role(role)
                                    .isEnabled(false)
                                    .isDeleted(false)
                                    .isAccountNonLocked(true)
                                    .build();

            employeeDAO.save(user);
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(token).build();
        } catch (Exception e) {
            //log error
            throw new RegisterException("/api/v1/auth/register", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server " +
                    "error", e.getMessage(), LocalDateTime.now().toString());
        }
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticateException {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new AuthenticateException("/api/v1/auth/authenticate", HttpStatus.INTERNAL_SERVER_ERROR, "Please " +
                    "provide username and password", null, LocalDateTime.now().toString());
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        var user = employeeDAO.findByUsername(request.getUsername());

        if (user.isPresent()) {
            var token = jwtService.generateToken(user.get());
            return AuthenticationResponse.builder().token(token).build();
        }

        return null;
    }

    private String generateUserName(String firstName, String lastName) {
        return lastName + "_" + firstName;
    }

    private static RoleEnum getRoleEnum(RegisterRequest request) {
        RoleEnum role = request.getRole();

        if (role == null) {
            role = RoleEnum.ROLE_EMPLOYEE;
        }
        return role;
    }
}
