public class BeanSprouts extends Topping {
    public BeanSprouts(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Bean Sprouts Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 0.67;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 0.68;
        }
        return roll.cost();
    }
}
