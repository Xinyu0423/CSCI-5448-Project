public class SpicyMayo extends Sauce {

    public SpicyMayo(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Spicy Mayo Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 0.30;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 0.35;
        } else if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 0.40;
        }
        return roll.cost();
    }
}
