package surfingnerd.inc.sever.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class RegisterException extends Throwable {


    private final String path;
    private final HttpStatus status;
    private final String message;
    private final String error;
    private final String timestamp;
}
