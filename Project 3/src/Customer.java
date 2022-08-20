import java.util.Random;

public abstract class Customer {
    protected String customerType;
    private Random rand = new Random();

    public int getExtraSauces() {
        return rand.nextInt(4);
    }

    public int getExtraFillings() {
        return rand.nextInt(2);
    }

    public int getExtraToppings() {
        return rand.nextInt(3);
    }

    public String getCustomerType() {
        return customerType;
    }

    abstract public String getOutOfStockBehavior();

    abstract public int[] getOrder(String[] rollTypes);
}
