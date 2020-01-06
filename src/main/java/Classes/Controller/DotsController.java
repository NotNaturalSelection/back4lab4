package Classes.Controller;

import Classes.DataClasses.Dot;
import Classes.DataClasses.DotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    List<Dot> getDots(){
        return dotsService.getDots();
    }

    @PostMapping
    Dot saveDot(@RequestBody Dot dot){
        dotsService.saveDot(dot);
        return dot;
    }
}
