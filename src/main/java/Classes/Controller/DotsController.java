package Classes.Controller;

import Classes.DataClasses.Dot;
import Classes.DataClasses.DotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "dots")
public class DotsController {

    DotsService dotsService;

    @Autowired
    public DotsController(DotsService dotsService) {
        this.dotsService = dotsService;
    }

    @GetMapping
    List<Dot> getDots(@RequestHeader(value = "Authorization") String credentials) {
        List<Dot> list = dotsService.getDotsByUsername(decodeLoginFromHeaderBasic64(credentials));
        if (list.size() <= 5) {
            return list;
        } else {
            return list.subList(list.size() - 5, list.size());
        }
    }

    @PostMapping
    Dot saveDot(@RequestHeader(value = "Authorization") String credentials, @RequestBody Dot dot) {
        dot.setOwner(decodeLoginFromHeaderBasic64(credentials));
        dotsService.saveDot(dot);
        return dot;
    }

    private String decodeLoginFromHeaderBasic64(String header){
        header = header.replace("Basic ", "");
        header = new String(java.util.Base64.getDecoder().decode(header));
        return header.split(":")[0];
    }
}
