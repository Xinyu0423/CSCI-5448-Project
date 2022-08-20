import java.util.Random;

public class CasualCustomer extends Customer {
    public CasualCustomer() {
        customerType = "Casual";
    }

    // Order 1-3 rolls of a random type
    public int[] getOrder(String[] rollTypes) {
        Random rand = new Random();
        int[] order = new int[rollTypes.length];

        // Get index for customer's roll choice.
        int choice = rand.nextInt(rollTypes.length);

        // Casual customers order 1-3 rolls of a specific type.
        int amount = rand.nextInt(3) + 1;

        for (int i = 0; i < rollTypes.length; i++) {
            order[i] = i == choice ? amount : 0;
        }
        return order;
    }

    // Casual customers are flexible with the type and quantity of rolls ordered
    public String getOutOfStockBehavior() {
        return "FlexibleSingleType";
    }

}
