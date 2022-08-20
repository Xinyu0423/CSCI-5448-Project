import java.util.ArrayList;
import java.util.Collections;

public class CateringCustomer extends Customer {
    public CateringCustomer() {
        customerType = "Catering";
    }

    // Order 5 rolls of three random types
    public int[] getOrder(String[] rollTypes) {
        int[] order = new int[rollTypes.length];

        ArrayList<Integer> choices = new ArrayList<>();
        for (int i = 0; i < rollTypes.length; i++) {
            choices.add(i);
        }

        Collections.shuffle(choices);
        for (int i = 0; i < 3; i++) {
            order[choices.get(i)] = 5;
        }

        return order;
    }

    // Catering customers are fine with any type of roll as long as they can get the total amount requested
    public String getOutOfStockBehavior() {
        return "AnyType";
    }

}
