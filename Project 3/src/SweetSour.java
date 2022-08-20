public class SweetSour extends Sauce {

    public SweetSour(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Sweet & Sour Sauce";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Spring Roll")) {
            return roll.cost() + 0.20;
        } else if (baseType.equalsIgnoreCase("Egg Roll")) {
            return roll.cost() + 0.25;
        } else if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 0.22;
        }
        return roll.cost();
    }
}
