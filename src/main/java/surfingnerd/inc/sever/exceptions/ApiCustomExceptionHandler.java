package surfingnerd.inc.sever.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiCustomExceptionHandler {

   @ExceptionHandler(value = {RegisterException.class})
   public ResponseEntity<Object> handleAPIException(String uri, String message, HttpStatus status, String timestamp, RegisterException e) {

      RegisterException apiException = new RegisterException(
              uri,
              status,
              message,
              e.getMessage(),
              timestamp);

       return ResponseEntity.status(status).body(apiException);
   }
}
