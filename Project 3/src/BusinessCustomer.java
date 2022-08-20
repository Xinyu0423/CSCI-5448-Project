public class BusinessCustomer extends Customer {
    public BusinessCustomer() {
        customerType = "Business";
    }

    // Order 2 rolls of each type
    public int[] getOrder(String[] rollTypes) {
        int[] order = new int[rollTypes.length];
        for (int i = 0; i < rollTypes.length; i++) {
            order[i] = 2;
        }
        return order;
    }

    // Leave the store if their order cannot be fulfilled
    public String getOutOfStockBehavior() {
        return "Leave";
    }
}
    