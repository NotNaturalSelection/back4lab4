package Classes.DataClasses;

import Classes.Controller.WrongCredentialsException;
import Classes.SpringRepository.UserRepository;
import Classes.SpringSecurity.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsersService  {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User loadUserByUsername(String username)  {
        if(isUserByUsernameExists(username)){
            return userRepository.findUserByUsername(username);
        } else {
            return null;
        }
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isUserByUsernameExists(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    public boolean isCredentialsValid(String username, String password) {
        if(isUserByUsernameExists(username)){
            User user = loadUserByUsername(username);
            return passwordEncoder.decode(user.getPassword()).equals(password);
        } else {
            return false;
        }
    }
}
