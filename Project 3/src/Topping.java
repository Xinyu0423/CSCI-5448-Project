// Extra topping decorator, specific toppings will extend this class
public class Topping extends ExtrasDecorator {
    // Roll that is being decorated
    protected Roll roll;
    // Base type of the roll doesn't change with added extras
    protected String baseType;

    public Topping(Roll roll) {
        this.roll = roll;
        this.baseType = roll.getBaseType();
    }

    public String rollInfo() {
        return roll.rollInfo() + ", extra topping";
    }

    public String getName() {
        return roll.getName() + ", Extra Topping";
    }

    public String getBaseType() {
        return baseType;
    }

    public double cost() {
        return roll.cost();
    }
}
