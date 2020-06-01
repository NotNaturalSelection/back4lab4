package Classes.MBeans;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class AreaCalculator implements AreaCalculatorMBean {
    private double area;

    public AreaCalculator() {
        this.setArea(calculateArea(1d));
    }

    @Override
    @ManagedAttribute
    public double getArea() {
        return this.area;
    }

    @ManagedAttribute
    public void setArea(double area) {
        this.area = area;
    }

    public double calculateArea(double r) {
        double triangleArea = 0.5 * Math.pow(0.5 * r, 2);
        double rectangleArea = 0.5 * Math.pow(r, 2);
        double circularSectorArea = Math.PI * Math.pow(r, 2) / 4;
        return triangleArea + rectangleArea + circularSectorArea;
    }
}
