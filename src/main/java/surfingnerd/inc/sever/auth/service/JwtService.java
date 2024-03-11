package surfingnerd.inc.sever.auth.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {

    String extractUsernameFromToken(String jwt);
    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);
    String generateToken(Map<String, Object> claims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String string, UserDetails userDetails);
}
