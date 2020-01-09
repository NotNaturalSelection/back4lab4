package Classes.Controller;

import Classes.DataClasses.User;
import Classes.DataClasses.UsersService;
import Classes.SpringSecurity.HeaderDecoder;
import Classes.SpringSecurity.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "logging", method = RequestMethod.POST)
public class LoginController {

    UsersService usersService;

    HeaderDecoder headerDecoder;

    PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UsersService usersService, HeaderDecoder headerDecoder, PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.headerDecoder = headerDecoder;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    void logIn(@RequestHeader(name = "Authorization") String header) throws WrongCredentialsException {
        String username = headerDecoder.decodeLoginFromHeaderBasic64(header);
        String password = headerDecoder.decodePasswordFromHeaderBasic64(header);
        if(usersService.isUserByUsernameExists(username)){
            User user = usersService.loadUserByUsername(username);
            if(!passwordEncoder.decode(user.getPassword()).equals(password)){
                throw new WrongCredentialsException();
            }
        } else {
            throw new WrongCredentialsException();
        }
    }
}
