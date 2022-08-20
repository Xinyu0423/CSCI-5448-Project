public class Soy extends Sauce {

    public Soy(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Soy Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 0.10;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 0.15;
        } else if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 0.12;
        }
        return roll.cost();
    }
}
