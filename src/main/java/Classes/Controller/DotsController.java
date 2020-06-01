package Classes.Controller;

import Classes.DataClasses.Dot;
import Classes.DataClasses.DotsService;
import Classes.DataClasses.UsersService;
import Classes.MBeans.DotCounterMBean;
import Classes.SpringSecurity.HeaderDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dots")
public class DotsController {

    private DotsService dotsService;

    private UsersService usersService;

    private HeaderDecoder headerDecoder;

    private DotCounterMBean dotCounterMBean;

    @Autowired
    public DotsController(DotsService dotsService, UsersService usersService, HeaderDecoder headerDecoder, DotCounterMBean dotCounterMBean) {
        this.dotsService = dotsService;
        this.usersService = usersService;
        this.headerDecoder = headerDecoder;
        this.dotCounterMBean = dotCounterMBean;
    }

    @GetMapping
    List<Dot> getDots(@RequestHeader(value = "Authorization") String credentials) {
        if (usersService.isCredentialsValid(headerDecoder.decodeLoginFromHeaderBasic64(credentials), headerDecoder.decodePasswordFromHeaderBasic64(credentials))) {
            List<Dot> list = dotsService.getDotsByUsername(headerDecoder.decodeLoginFromHeaderBasic64(credentials));
            if (list.size() <= 5) {
                return list;
            } else {
                return list.subList(list.size() - 5, list.size());
            }
        } else {
            return new ArrayList<>();
        }
    }

    @PostMapping
    Dot saveDot(@RequestHeader(value = "Authorization") String credentials, @RequestBody Dot dot) {
        if (usersService.isCredentialsValid(headerDecoder.decodeLoginFromHeaderBasic64(credentials), headerDecoder.decodePasswordFromHeaderBasic64(credentials))) {
            dot.setOwner(headerDecoder.decodeLoginFromHeaderBasic64(credentials));
            dotsService.saveDot(dot);
            dotCounterMBean.setDotAmount(dotCounterMBean.getDotAmount() + 1);
            if (dot.isHit()) dotCounterMBean.setHitAmount(dotCounterMBean.getHitAmount() + 1);
            dotCounterMBean.sendTwoMissesNotification(dot.getX(), dot.getY(), dot.getR(), dot.isHit());
            return dot;
        } else {
            return new Dot();
        }
    }

}
