public class Nutella extends Sauce {

    public Nutella(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Nutella Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 0.55;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 0.60;
        }
        return roll.cost();
    }
}
