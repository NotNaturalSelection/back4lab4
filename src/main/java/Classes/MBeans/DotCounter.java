package Classes.MBeans;

import Classes.DataClasses.DotsService;
import Classes.DataClasses.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

@ManagedResource
@Component
public class DotCounter extends NotificationBroadcasterSupport implements DotCounterMBean {
    private boolean isLastMissed = false;
    private DotsService dotsService;
    private UsersService usersService;
    private long dotAmount;
    private int hitAmount;
    private int sequenceNumber;

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

    public void increaseHitAmount() {
        this.hitAmount++;
    }

    public void increaseDotAmount() {
        this.dotAmount++;
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

    private boolean checkTwoMisses(boolean hit) {
        boolean isPreviousMissed = this.isLastMissed();
        this.isLastMissed = !hit;
        return this.isLastMissed && isPreviousMissed;
    }

    public void sendTwoMissesNotification(double x, double y, double r, boolean hit) {
        if (checkTwoMisses(hit))
            sendNotification(new Notification(
                    "TwoMisses",
                    this, sequenceNumber++,
                    "Промах был в точке с координатами (" + x + ";" + y + ") при радиусе " + r + ".\n" +
                            "Удача явно не на вашей стороне. Вы не попали минимум два раза подряд.")
            );
    }
}
