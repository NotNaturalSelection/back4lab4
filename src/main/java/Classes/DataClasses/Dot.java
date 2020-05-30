package Classes.DataClasses;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dots", schema = "public")
public class Dot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator( name = "jpaSequence", sequenceName = "JPA_SEQUENCE1", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "r")
    private double r;

    @Column(name = "is_hit")
    private boolean hit;

    @Column(name = "owner")
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot dot = (Dot) o;
        return Double.compare(dot.x, x) == 0 &&
                Double.compare(dot.y, y) == 0 &&
                Double.compare(dot.r, r) == 0 &&
                hit == dot.hit &&
                Objects.equals(id, dot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, hit);
    }

    @Override
    public String toString() {
        return "Dot{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                '}';
    }
}
