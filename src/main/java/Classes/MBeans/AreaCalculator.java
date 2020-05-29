package Classes.MBeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource
@Component
public class AreaCalculator implements AreaCalculatorMBean {
    private double r;
    public AreaCalculator(){
        this.setR(1d);
    }
    @Override
    @ManagedAttribute
    public double getR() {
        return this.r;
    }
    @ManagedAttribute
    public void setR(double r) {
        this.r = r;
    }

    private double calculateArea() {
        double triangleArea = 0.5 * Math.pow(0.5 * r, 2);
        double rectangleArea = 0.5 * Math.pow(r, 2);
        double circularSectorArea = Math.PI * Math.pow(r, 2) / 4;
        return triangleArea + rectangleArea + circularSectorArea;
    }

    @Override
    @ManagedOperation
    public void printArea() {
        System.out.println(calculateArea());
    }
}
