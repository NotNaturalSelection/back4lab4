package Classes.Controller;

import Classes.MBeans.AreaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "changingRadius", method = RequestMethod.POST)
public class RadiusController {
    @Autowired
    private AreaCalculator areaCalculator;
    @GetMapping("{radius}")
    public void radiusChange(@PathVariable double radius){
        areaCalculator.setR(radius);
    }
}
