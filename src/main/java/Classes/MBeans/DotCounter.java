package Classes.MBeans;

import Classes.DataClasses.Dot;
import Classes.DataClasses.DotsService;
import Classes.DataClasses.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class DotCounter implements DotCounterMBean {
    private boolean isLastMissed = false;
    private DotsService dotsService;
    private UsersService usersService;
    private long dotAmount;
    private int hitAmount;

    @Autowired
    public DotCounter(DotsService dotsService, UsersService usersService) {
        this.dotAmount = dotsService.getDotAmount();
        this.hitAmount = dotsService.getHitAmount();
        this.dotsService = dotsService;
        this.usersService = usersService;
    }

    @ManagedAttribute
    public int getHitAmount() {
        return hitAmount;
    }

    @ManagedAttribute
    public long getDotAmount() {
        return dotAmount;
    }

    @ManagedAttribute
    public boolean isLastMissed() {
        return isLastMissed;
    }

    @ManagedAttribute
    public void setLastMissed(boolean lastMissed) {
        isLastMissed = lastMissed;
    }

    @ManagedAttribute
    public void setHitAmount(int hitAmount) {
        this.hitAmount = hitAmount;
    }

    @ManagedAttribute
    public void setDotAmount(long dotAmount) {
        this.dotAmount = dotAmount;
    }

    @ManagedOperation
    public int getUserDotAmount(String username) {
        int amount = 0;
        if (usersService.findUserByUsername(username) != null) {
            amount = dotsService.getDotAmount(username);
        }
        return amount;
    }

    @ManagedOperation
    public int getUserHitAmount(String username) {
        int amount = 0;
        if (usersService.findUserByUsername(username) != null) {
            amount = dotsService.getHitAmount(username);
        }
        return amount;
    }

//    public void checkTwoMisses(Dot dot) {
//        boolean isPreviousMissed = this.isLastMissed();
//        this.isLastMissed = !dot.isHit();
//        System.out.println(this.isLastMissed + " dd" + isPreviousMissed);
//        if (this.isLastMissed && isPreviousMissed) {
//            printTwoMisses();
//        }
//    }
//
//    private void printTwoMisses() {
//        System.out.println("Удача явно не на вашей стороне. Вы не попали минимум два раза подряд.");
//    }
}
