public class Sugar extends Topping {

    public Sugar(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Sugar Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Pastry Roll")) {
            return roll.cost() + 0.80;
        } else if (baseType.equalsIgnoreCase("Jelly Roll")) {
            return roll.cost() + 0.85;
        }
        return roll.cost();
    }
}
