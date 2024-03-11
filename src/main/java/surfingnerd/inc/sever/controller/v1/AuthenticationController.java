package surfingnerd.inc.sever.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import surfingnerd.inc.sever.auth.service.AuthenticationService;
import surfingnerd.inc.sever.dto.auth.AuthenticationRequest;
import surfingnerd.inc.sever.dto.auth.AuthenticationResponse;
import surfingnerd.inc.sever.dto.auth.RegisterRequest;
import surfingnerd.inc.sever.exceptions.AuthenticateException;
import surfingnerd.inc.sever.exceptions.RegisterException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws RegisterException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) throws AuthenticateException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
