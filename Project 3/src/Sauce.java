// Extra sauce decorator, specific sauces will extend this class
public class Sauce extends ExtrasDecorator {
    protected Roll roll;
    protected String baseType;

    public Sauce(Roll roll) {
        this.roll = roll;
        this.baseType = roll.getBaseType();
    }

    public String rollInfo() {
        return roll.rollInfo() + ", extra sauce";
    }

    public String getName() {
        return roll.getName() + ", Extra Sauce";
    }

    public String getBaseType() {
        return baseType;
    }

    public double cost() {
        return roll.cost();
    }
}
