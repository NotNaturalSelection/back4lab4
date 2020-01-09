package Classes.SpringSecurity;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PasswordEncoder {

    public String encode(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public String decode(String str){
        return new String(Base64.getDecoder().decode(str.getBytes()));
    }
}
