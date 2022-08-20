public class Cheese extends Topping {
    public Cheese(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Cheese Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 0.90;
        }
        return roll.cost();
    }
}
