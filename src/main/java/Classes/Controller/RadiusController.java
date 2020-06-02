package Classes.Controller;

import Classes.MBeans.AreaCalculatorMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "changingRadius", method = RequestMethod.POST)
public class RadiusController {
    private final AreaCalculatorMBean areaCalculatorMBean;

    @Autowired
    public RadiusController(AreaCalculatorMBean areaCalculatorMBean) {
        this.areaCalculatorMBean = areaCalculatorMBean;
    }

    @GetMapping("{radius}")
    public void radiusChange(@PathVariable double radius){
        areaCalculatorMBean.calculateArea(radius);
    }
}
