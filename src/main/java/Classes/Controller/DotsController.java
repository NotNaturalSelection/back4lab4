package Classes.Controller;

import Classes.DataClasses.Dot;
import Classes.DataClasses.DotsService;
import Classes.SpringSecurity.HeaderDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "dots")
public class DotsController {

    DotsService dotsService;

    HeaderDecoder headerDecoder;

    @Autowired
    public DotsController(DotsService dotsService, HeaderDecoder headerDecoder) {
        this.dotsService = dotsService;
        this.headerDecoder = headerDecoder;
    }

    @GetMapping
    List<Dot> getDots(@RequestHeader(value = "Authorization") String credentials) {
        List<Dot> list = dotsService.getDotsByUsername(headerDecoder.decodeLoginFromHeaderBasic64(credentials));
        if (list.size() <= 5) {
            return list;
        } else {
            return list.subList(list.size() - 5, list.size());
        }
    }

    @PostMapping
    Dot saveDot(@RequestHeader(value = "Authorization") String credentials, @RequestBody Dot dot) {
        dot.setOwner(headerDecoder.decodeLoginFromHeaderBasic64(credentials));
        dotsService.saveDot(dot);
        return dot;
    }

}
