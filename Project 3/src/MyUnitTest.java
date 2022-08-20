import org.junit.Test;

import static org.junit.Assert.*;


public class MyUnitTest {
    public static String[] rollTypes = {"Egg Roll", "Jelly Roll", "Pastry Roll", "Sausage Roll", "Spring Roll"};
    public static String[] customerTypes = {"Business", "Casual", "Catering"};

    @Test
    public void testInventory() {
        System.out.println("Begin testInventory()");
        int restockAmount = 5;
        StoreInventory inventory = new StoreInventory(rollTypes, restockAmount);

        System.out.println("\tTesting StoreInventory getItemQuantity()...");
        assertEquals(restockAmount, inventory.getItemQuantity(rollTypes[0]));

        System.out.println("\tTesting StoreInventory getInventoryQuantity()...");
        assertEquals(restockAmount * rollTypes.length, inventory.getInventoryQuantity());

        System.out.println("\tTesting StoreInventory itemEmpty()...");
        for (int i = 0; i < restockAmount; i++) {
            inventory.buyItem(rollTypes[0]);
        }
        assertTrue(inventory.itemEmpty(rollTypes[0]));

        System.out.println("\tTesting StoreInventory inventoryEmpty()...");
        assertFalse(inventory.inventoryEmpty());

        System.out.println("\tTesting StoreInventory restock()...");
        assertEquals(0, inventory.getItemQuantity(rollTypes[0]));
        inventory.restock();
        assertEquals(restockAmount, inventory.getItemQuantity(rollTypes[0]));
    }

    @Test
    public void testRollFactory() {
        System.out.println("Begin testRollFactory()");

        RollFactory rollFactory = new RollFactory();

        System.out.println("\tTesting Create Egg Roll...");
        Roll eggRoll = rollFactory.createRoll("Egg Roll");
        assertEquals(eggRoll.getName(), new EggRoll().getName());

        System.out.println("\tTesting Create Jelly Roll...");
        Roll jellyRoll = rollFactory.createRoll("Jelly Roll");
        assertEquals(jellyRoll.getName(), new JellyRoll().getName());

        System.out.println("\tTesting Create Pastry Roll...");
        Roll pastryRoll = rollFactory.createRoll("Pastry Roll");
        assertEquals(pastryRoll.getName(), new PastryRoll().getName());

        System.out.println("\tTesting Create Sausage Roll...");
        Roll sausageRoll = rollFactory.createRoll("Sausage Roll");
        assertEquals(sausageRoll.getName(), new SausageRoll().getName());

        System.out.println("\tTesting Create Spring Roll...");
        Roll springRoll = rollFactory.createRoll("Spring Roll");
        assertEquals(springRoll.getName(), new SpringRoll().getName());
    }

    @Test
    public void testCustomerFactory() {
        System.out.println("Begin testCustomerFactory()");

        CustomerFactory customerFactory = new CustomerFactory();

        System.out.println("\tTesting Create Business Customer...");
        Customer businessCustomer = customerFactory.createCustomer("Business");
        assertEquals(businessCustomer.getCustomerType(), new BusinessCustomer().getCustomerType());

        System.out.println("\tTesting Create Casual Customer...");
        Customer casualCustomer = customerFactory.createCustomer("Casual");
        assertEquals(casualCustomer.getCustomerType(), new CasualCustomer().getCustomerType());

        System.out.println("\tTesting Create Catering Customer...");
        Customer cateringCustomer = customerFactory.createCustomer("Catering");
        assertEquals(cateringCustomer.getCustomerType(), new CateringCustomer().getCustomerType());
    }

    @Test
    public void testRolls() {
        System.out.println("Begin testRolls()");

        System.out.println("\tTesting Egg Roll...");
        EggRoll eggRoll = new EggRoll();
        assertEquals("Egg Roll", eggRoll.rollInfo());
        assertEquals(2.50, eggRoll.cost(), .01);

        System.out.println("\tTesting Jelly Roll...");
        JellyRoll jellyRoll = new JellyRoll();
        assertEquals("Jelly Roll", jellyRoll.rollInfo());
        assertEquals(2.25, jellyRoll.cost(), .01);

        System.out.println("\tTesting Pasty Roll...");
        PastryRoll pastryRoll = new PastryRoll();
        assertEquals("Pastry Roll", pastryRoll.rollInfo());
        assertEquals(2.00, pastryRoll.cost(), .01);

        System.out.println("\tTesting Sausage Roll...");
        SausageRoll sausageRoll = new SausageRoll();
        assertEquals("Sausage Roll", sausageRoll.rollInfo());
        assertEquals(3.50, sausageRoll.cost(), .01);

        System.out.println("\tTesting Spring Roll...");
        SpringRoll springRoll = new SpringRoll();
        assertEquals("Spring Roll", springRoll.rollInfo());
        assertEquals(3.00, springRoll.cost(), .01);
    }


    @Test
    public void testCustomer() {
        System.out.println("Begin testCustomer()");

        System.out.println("\tTesting Business Customer...");
        BusinessCustomer businessCustomer = new BusinessCustomer();
        int[] businessOrderExpected = {2, 2, 2, 2, 2};
        assertEquals("Business", businessCustomer.getCustomerType());
        assertArrayEquals(businessOrderExpected, businessCustomer.getOrder(rollTypes));
        assertEquals("Leave", businessCustomer.getOutOfStockBehavior());

        System.out.println("\tTesting Casual Customer...");
        CasualCustomer casualCustomer = new CasualCustomer();
        assertEquals("Casual", casualCustomer.getCustomerType());
        assertEquals("FlexibleSingleType", casualCustomer.getOutOfStockBehavior());

        System.out.println("\tTesting Catering Customer...");
        CateringCustomer cateringCustomer = new CateringCustomer();
        assertEquals("Catering", cateringCustomer.getCustomerType());
        assertEquals("AnyType", cateringCustomer.getOutOfStockBehavior());
    }

    @Test
    public void testFillings() {
        System.out.println("Begin testFillings()");
        Roll roll;
        System.out.println("\tTesting Egg Roll Fillings...");
        roll = new Filling(new EggRoll());
        assertEquals("Egg Roll, Meat Filling", roll.rollInfo());
        assertEquals(3.5, roll.cost(), 0.01);
        assertEquals("Egg Roll, filling", roll.getName());
        assertEquals("Egg Roll", roll.getBaseType());

        System.out.println("\tTesting Spring Roll Fillings...");
        roll = new Filling(new SpringRoll());
        assertEquals("Spring Roll, Meat Filling", roll.rollInfo());
        assertEquals(4.25, roll.cost(), 0.01);
        assertEquals("Spring Roll, filling", roll.getName());
        assertEquals("Spring Roll", roll.getBaseType());

        System.out.println("\tTesting Pastry Roll Fillings...");
        roll = new Filling(new PastryRoll());
        assertEquals("Pastry Roll, Cheese Filling", roll.rollInfo());
        assertEquals(3.5, roll.cost(), 0.01);
        assertEquals("Pastry Roll, filling", roll.getName());
        assertEquals("Pastry Roll", roll.getBaseType());

        System.out.println("\tTesting Sausage Roll Fillings...");
        roll = new Filling(new SausageRoll());
        assertEquals("Sausage Roll, Cheese Filling", roll.rollInfo());
        assertEquals(5.25, roll.cost(), 0.01);
        assertEquals("Sausage Roll, filling", roll.getName());
        assertEquals("Sausage Roll", roll.getBaseType());

        System.out.println("\tTesting Jelly Roll Fillings...");
        roll = new Filling(new JellyRoll());
        assertEquals("Jelly Roll, Frosting Filling", roll.rollInfo());
        assertEquals(3.5, roll.cost(), 0.01);
        assertEquals("Jelly Roll, filling", roll.getName());
        assertEquals("Jelly Roll", roll.getBaseType());
    }

    @Test
    public void testToppings() {
        System.out.println("Begin testToppings()");
        Roll roll;

        System.out.println("\tTesting Shredded Carrot Topping...");
        roll = new ShreddedCarrot(new SpringRoll());
        assertEquals("Spring Roll, Shredded Carrot Topping", roll.rollInfo());
        assertEquals(3.77, roll.cost(), .01);

        roll = new ShreddedCarrot((new EggRoll()));
        assertEquals("Egg Roll, Shredded Carrot Topping", roll.rollInfo());
        assertEquals(3.28, roll.cost(), .01);

        System.out.println("\tTesting Bean Sprouts Topping...");
        roll = new BeanSprouts(new SpringRoll());
        assertEquals("Spring Roll, Bean Sprouts Topping", roll.rollInfo());
        assertEquals(3.67, roll.cost(), .01);

        roll = new BeanSprouts((new EggRoll()));
        assertEquals("Egg Roll, Bean Sprouts Topping", roll.rollInfo());
        assertEquals(3.18, roll.cost(), .01);

        System.out.println("\tTesting Sugar Topping...");
        roll = new Sugar(new PastryRoll());
        assertEquals("Pastry Roll, Sugar Topping", roll.rollInfo());
        assertEquals(2.8, roll.cost(), .01);

        roll = new Sugar((new JellyRoll()));
        assertEquals("Jelly Roll, Sugar Topping", roll.rollInfo());
        assertEquals(3.10, roll.cost(), .01);

        System.out.println("\tTesting Chocolate Topping...");
        roll = new Chocolate(new PastryRoll());
        assertEquals("Pastry Roll, Chocolate Topping", roll.rollInfo());
        assertEquals(3.15, roll.cost(), .01);

        roll = new Chocolate((new JellyRoll()));
        assertEquals("Jelly Roll, Chocolate Topping", roll.rollInfo());
        assertEquals(3.45, roll.cost(), .01);

        System.out.println("\tTesting Cheese Topping...");
        roll = new Cheese(new SausageRoll());
        assertEquals("Sausage Roll, Cheese Topping", roll.rollInfo());
        assertEquals(4.40, roll.cost(), .01);

        System.out.println("\tTesting Bacon Bits Topping...");
        roll = new BaconBits(new SausageRoll());
        assertEquals("Sausage Roll, Bacon Bits Topping", roll.rollInfo());
        assertEquals(4.45, roll.cost(), .01);
    }

    @Test
    public void testSauces() {
        System.out.println("Begin testSauces()");
        Roll roll;
        System.out.println("\tTesting Soy Sauce...");
        roll = new Soy(new SpringRoll());
        assertEquals("Spring Roll, Soy Sauce", roll.rollInfo());
        assertEquals(3.1, roll.cost(), .01);

        roll = new Soy(new EggRoll());
        assertEquals("Egg Roll, Soy Sauce", roll.rollInfo());
        assertEquals(2.65, roll.cost(), .01);

        roll = new Soy(new SausageRoll());
        assertEquals("Sausage Roll, Soy Sauce", roll.rollInfo());
        assertEquals(3.62, roll.cost(), .01);

        System.out.println("\tTesting Spicy Mayo Sauce...");
        roll = new SpicyMayo(new SpringRoll());
        assertEquals("Spring Roll, Spicy Mayo Sauce", roll.rollInfo());
        assertEquals(3.3, roll.cost(), .01);

        roll = new SpicyMayo(new EggRoll());
        assertEquals("Egg Roll, Spicy Mayo Sauce", roll.rollInfo());
        assertEquals(2.85, roll.cost(), .01);

        roll = new SpicyMayo(new SausageRoll());
        assertEquals("Sausage Roll, Spicy Mayo Sauce", roll.rollInfo());
        assertEquals(3.90, roll.cost(), .01);


        System.out.println("\tTesting Sweet & Sour Sauce...");
        roll = new SweetSour(new SpringRoll());
        assertEquals("Spring Roll, Sweet & Sour Sauce", roll.rollInfo());
        assertEquals(3.2, roll.cost(), .01);

        roll = new SweetSour(new EggRoll());
        assertEquals("Egg Roll, Sweet & Sour Sauce", roll.rollInfo());
        assertEquals(2.75, roll.cost(), .01);

        roll = new SweetSour(new SausageRoll());
        assertEquals("Sausage Roll, Sweet & Sour Sauce", roll.rollInfo());
        assertEquals(3.72, roll.cost(), .01);

        System.out.println("\tTesting Cranberry Sauce...");
        roll = new Cranberry(new PastryRoll());
        assertEquals("Pastry Roll, Cranberry Sauce", roll.rollInfo());
        assertEquals(2.65, roll.cost(), .01);

        roll = new Cranberry(new JellyRoll());
        assertEquals("Jelly Roll, Cranberry Sauce", roll.rollInfo());
        assertEquals(2.95, roll.cost(), .01);

        System.out.println("\tTesting Honey Sauce...");
        roll = new Honey(new PastryRoll());
        assertEquals("Pastry Roll, Honey Sauce", roll.rollInfo());
        assertEquals(2.23, roll.cost(), .01);

        roll = new Honey(new JellyRoll());
        assertEquals("Jelly Roll, Honey Sauce", roll.rollInfo());
        assertEquals(2.49, roll.cost(), .01);

        System.out.println("\tTesting Nutella Sauce...");
        roll = new Nutella(new PastryRoll());
        assertEquals("Pastry Roll, Nutella Sauce", roll.rollInfo());
        assertEquals(2.55, roll.cost(), .01);

        roll = new Nutella(new JellyRoll());
        assertEquals("Jelly Roll, Nutella Sauce", roll.rollInfo());
        assertEquals(2.85, roll.cost(), .01);
    }

    @Test
    public void testExtrasFactory() {
        System.out.println("Begin testExtrasFactory()");

        ExtrasFactory extrasFactory = new ExtrasFactory();

        System.out.println("\tTesting getFilling()...");
        Roll springRollFilling = extrasFactory.getFilling(1, new SpringRoll());
        assertEquals(new Filling(new SpringRoll()).rollInfo(), springRollFilling.rollInfo());

        Roll sausageRollFilling = extrasFactory.getFilling(1, new SausageRoll());
        assertEquals(new Filling(new SausageRoll()).rollInfo(), sausageRollFilling.rollInfo());

        System.out.println("\tTesting getTopping()...");
        Roll pastryRollTopping = extrasFactory.getTopping(2, new PastryRoll());
        assertEquals(new Chocolate(new Sugar(new PastryRoll())).rollInfo(), pastryRollTopping.rollInfo());

        Roll eggRollTopping = extrasFactory.getTopping(2, new EggRoll());
        assertEquals(new BeanSprouts(new ShreddedCarrot(new EggRoll())).rollInfo(), eggRollTopping.rollInfo());

        System.out.println("\tTesting getSauce()...");
        Roll jellyRollSauce = extrasFactory.getSauce(3, new JellyRoll());
        assertEquals(new Cranberry(new Honey(new Nutella(new JellyRoll()))).rollInfo(), jellyRollSauce.rollInfo());
    }

    @Test
    public void testStoreSalesManager() {
        System.out.println("Begin testStoreSalesManager()");

        System.out.println("\tTesting customerTransaction()...");
        StoreInventory inventory1 = new StoreInventory(rollTypes, 30);
        StoreInventory inventory2 = new StoreInventory(rollTypes, 1);

        StoreSalesManager salesManager = new StoreSalesManager(rollTypes);
        salesManager.setInventory(inventory1);

        BusinessCustomer customer = new BusinessCustomer();

        assertTrue(salesManager.customerTransaction(customer));

        salesManager.setInventory(inventory2);
        assertFalse(salesManager.customerTransaction(customer));
    }
}


