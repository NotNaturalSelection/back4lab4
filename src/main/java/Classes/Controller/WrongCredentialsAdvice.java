package Classes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WrongCredentialsAdvice {
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    @ExceptionHandler(WrongCredentialsException.class)
    public void handleConflict() {
    }
}
