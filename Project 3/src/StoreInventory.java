public class StoreInventory {
    int restockAmount;
    private String[] names;
    private int[] counts;

    public StoreInventory(String[] itemNames, int restockAmount) {
        this.names = itemNames;
        this.restockAmount = restockAmount;
        counts = new int[names.length];

        // Initial inventory stock
        for (int i = 0; i < names.length; i++) {
            counts[i] = restockAmount;
        }
    }

    // Get the quantity of a specified item
    public int getItemQuantity(String itemName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(itemName)) {
                return counts[i];
            }
        }
        return -1;
    }

    // Get total quantity of the inventory
    public int getInventoryQuantity() {
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            sum += counts[i];
        }
        return sum;
    }

    // Check if item is sold out
    public boolean itemEmpty(String itemName) {
        return getItemQuantity(itemName) == 0;
    }

    // Check if the entire inventory is empty
    public boolean inventoryEmpty() {
        return getInventoryQuantity() == 0;
    }

    // Update inventory after item is bought
    public void buyItem(String itemName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(itemName)) {
                counts[i] -= 1;
            }
        }
    }

    // Restock all items which are sold out
    public void restock() {
        for (int i = 0; i < names.length; i++) {
            if (itemEmpty(names[i])) {
                counts[i] = restockAmount;
            }
        }
    }

    // Print the quantity of all the rolls in the inventory
    public void printRollInventory() {
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + "\t" + counts[i]);
        }
    }
}
