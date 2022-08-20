public class Chocolate extends Topping {

    public Chocolate(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Chocolate Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 1.15;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 1.20;
        }
        return roll.cost();
    }
}
