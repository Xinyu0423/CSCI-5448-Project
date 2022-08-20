public class Honey extends Sauce {

    public Honey(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Honey Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 0.23;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 0.24;
        }
        return roll.cost();
    }
}
