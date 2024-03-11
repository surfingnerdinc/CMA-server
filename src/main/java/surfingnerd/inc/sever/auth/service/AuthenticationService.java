package surfingnerd.inc.sever.auth.service;

import org.springframework.stereotype.Service;
import surfingnerd.inc.sever.dto.auth.AuthenticationRequest;
import surfingnerd.inc.sever.dto.auth.AuthenticationResponse;
import surfingnerd.inc.sever.dto.auth.RegisterRequest;
import surfingnerd.inc.sever.exceptions.ApiCustomExceptionHandler;
import surfingnerd.inc.sever.exceptions.AuthenticateException;
import surfingnerd.inc.sever.exceptions.RegisterException;

@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request) throws RegisterException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticateException;
}
