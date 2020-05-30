package Classes.Controller;

import Classes.DataClasses.UsersService;
import Classes.SpringSecurity.HeaderDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "logging", method = RequestMethod.POST)
public class LoginController {

    private UsersService usersService;

    private HeaderDecoder headerDecoder;

    @Autowired
    public LoginController(UsersService usersService, HeaderDecoder headerDecoder) {
        this.usersService = usersService;
        this.headerDecoder = headerDecoder;
    }

    @PostMapping
    void logIn(@RequestHeader(name = "Authorization") String header) throws WrongCredentialsException {
        String username = headerDecoder.decodeLoginFromHeaderBasic64(header);
        String password = headerDecoder.decodePasswordFromHeaderBasic64(header);
        if (!usersService.isCredentialsValid(username, password)) {
            throw new WrongCredentialsException();
        }
    }
}
