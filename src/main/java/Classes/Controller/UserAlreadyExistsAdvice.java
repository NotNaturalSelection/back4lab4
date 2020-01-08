package Classes.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAlreadyExistsAdvice {
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    @ExceptionHandler(UserAlreadyExistsException.class)
    public void handleConflict() {

    }
}
