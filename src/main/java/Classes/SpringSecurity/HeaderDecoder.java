package Classes.SpringSecurity;

import org.springframework.stereotype.Component;

@Component
public class HeaderDecoder {

    public HeaderDecoder() {
    }

    public String decodeLoginFromHeaderBasic64(String header){
        header = header.replace("Basic ", "");
        header = new String(java.util.Base64.getDecoder().decode(header));
        return header.split(":")[0];
    }
    public String decodePasswordFromHeaderBasic64(String header){
        header = header.replace("Basic ", "");
        header = new String(java.util.Base64.getDecoder().decode(header));
        return header.split(":")[1];
    }
}
