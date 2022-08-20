public class Cranberry extends Sauce {
    public Cranberry(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Cranberry Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 0.65;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 0.70;
        }
        return roll.cost();
    }
}
