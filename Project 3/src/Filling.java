// Extra Filling Decorator
public class Filling extends ExtrasDecorator {
    // Roll that is being decorated
    protected Roll roll;
    // Base type of the roll doesn't change with added extras
    protected String baseType;

    public Filling(Roll roll) {
        this.roll = roll;
        this.baseType = roll.getBaseType();
    }

    // Return the correct filling info based on the roll's base type
    public String rollInfo() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.rollInfo() + ", Meat Filling";
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.rollInfo() + ", Meat Filling";
        } else if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.rollInfo() + ", Cheese Filling";
        } else if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.rollInfo() + ", Cheese Filling";
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.rollInfo() + ", Frosting Filling";
        }
        return roll.rollInfo();
    }

    public String getName() {
        return roll.getName() + ", filling";
    }

    // Return the correct price for the filling based on the roll's base type
    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 1.25;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 1.00;
        } else if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 1.50;
        } else if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 1.75;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 1.25;
        }
        return roll.cost();
    }

    public String getBaseType() {
        return baseType;
    }
}
