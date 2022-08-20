public class ShreddedCarrot extends Topping {

    public ShreddedCarrot(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Shredded Carrot Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 0.77;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 0.78;
        }
        return roll.cost();
    }
}
