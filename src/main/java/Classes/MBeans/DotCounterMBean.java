package Classes.MBeans;

public interface DotCounterMBean {
    long getDotAmount();

    int getHitAmount();

    void setDotAmount(long dotAmount);

    void setHitAmount(int hitAmount);

    int getUserDotAmount(String username);

    int getUserHitAmount(String username);

    void sendTwoMissesNotification(double x, double y, double r, boolean hit);
}
