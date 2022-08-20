public class BaconBits extends Topping {

    public BaconBits(Roll roll) {
        super(roll);
    }

    public String rollInfo() {
        return roll.rollInfo() + ", Bacon Bits Topping";
    }

    public double cost() {
        if (baseType.equalsIgnoreCase("Sausage Roll")) {
            return roll.cost() + 0.95;
        }
        return roll.cost();
    }
}
