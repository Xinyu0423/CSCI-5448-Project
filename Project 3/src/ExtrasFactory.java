import java.util.ArrayList;
import java.util.Random;

// Each roll only should have specific toppings,
// this class controls that the right toppings are added to each roll type
public class ExtrasFactory {

    // Get a filling for the specified roll
    public Roll getFilling(int num, Roll roll) {
        if (num == 0) {
            return roll;
        } else if (num == 1) {
            return new Filling(roll);
        }
        return roll;
    }

    // Get the specified number of toppings for each roll type
    public Roll getTopping(int num, Roll roll) {
        // Put all correct topping combinations in a list
        ArrayList<Topping> toppings = new ArrayList<>();
        if (roll.getBaseType().equalsIgnoreCase("Spring Roll")) {
            toppings.add(new ShreddedCarrot(roll));
            toppings.add(new BeanSprouts(roll));
            toppings.add(new BeanSprouts(new ShreddedCarrot(roll)));
        } else if (roll.getBaseType().equalsIgnoreCase("Egg Roll")) {
            toppings.add(new ShreddedCarrot(roll));
            toppings.add(new BeanSprouts(roll));
            toppings.add(new BeanSprouts(new ShreddedCarrot(roll)));
        } else if (roll.getBaseType().equalsIgnoreCase("Pastry Roll")) {
            toppings.add(new Sugar(roll));
            toppings.add(new Chocolate(roll));
            toppings.add(new Chocolate(new Sugar(roll)));
        } else if (roll.getBaseType().equalsIgnoreCase("Sausage Roll")) {
            toppings.add(new Cheese(roll));
            toppings.add(new BaconBits(roll));
            toppings.add(new BaconBits(new Cheese(roll)));
        } else if (roll.getBaseType().equalsIgnoreCase("Jelly Roll")) {
            toppings.add(new Sugar(roll));
            toppings.add(new Chocolate(roll));
            toppings.add(new Chocolate(new Sugar(roll)));
        }

        Random rand = new Random();
        // Select a random topping based on the specified number of toppings requested
        switch (num) {
            case 0:
                return roll;
            case 1:
                int rand1 = rand.nextInt(2);//choose between 0 and 1
                return toppings.get(rand1);
            case 2:
                return toppings.get(2);
        }
        return roll;
    }

    // Get the specified number of sauces for each roll type
    public Roll getSauce(int num, Roll roll) {
        // Put all the correct sauce combinations in a list
        ArrayList<Sauce> sauces = new ArrayList<>();
        if (roll.getBaseType().equalsIgnoreCase("Spring Roll")) {
            sauces.add(new Soy(roll));
            sauces.add(new SweetSour(roll));
            sauces.add(new SpicyMayo(roll));
            sauces.add(new Soy(new SweetSour(roll)));
            sauces.add(new Soy(new SpicyMayo(roll)));
            sauces.add(new SweetSour(new SpicyMayo(roll)));
            sauces.add(new Soy(new SweetSour(new SpicyMayo(roll))));
        } else if (roll.getBaseType().equalsIgnoreCase("Egg Roll")) {
            sauces.add(new Soy(roll));
            sauces.add(new SweetSour(roll));
            sauces.add(new SpicyMayo(roll));
            sauces.add(new Soy(new SweetSour(roll)));
            sauces.add(new Soy(new SpicyMayo(roll)));
            sauces.add(new SweetSour(new SpicyMayo(roll)));
            sauces.add(new Soy(new SweetSour(new SpicyMayo(roll))));
        } else if (roll.getBaseType().equalsIgnoreCase("Pastry Roll")) {
            sauces.add(new Cranberry(roll));
            sauces.add(new Honey(roll));
            sauces.add(new Nutella(roll));
            sauces.add(new Cranberry(new Honey(roll)));
            sauces.add(new Cranberry(new Nutella(roll)));
            sauces.add(new Nutella(new Honey(roll)));
            sauces.add(new Cranberry(new Honey(new Nutella(roll))));
        } else if (roll.getBaseType().equalsIgnoreCase("Sausage Roll")) {
            sauces.add(new Soy(roll));
            sauces.add(new SweetSour(roll));
            sauces.add(new SpicyMayo(roll));
            sauces.add(new Soy(new SweetSour(roll)));
            sauces.add(new Soy(new SpicyMayo(roll)));
            sauces.add(new SweetSour(new SpicyMayo(roll)));
            sauces.add(new Soy(new SweetSour(new SpicyMayo(roll))));
        } else if (roll.getBaseType().equalsIgnoreCase("Jelly Roll")) {
            sauces.add(new Cranberry(roll));
            sauces.add(new Honey(roll));
            sauces.add(new Nutella(roll));
            sauces.add(new Cranberry(new Honey(roll)));
            sauces.add(new Cranberry(new Nutella(roll)));
            sauces.add(new Nutella(new Honey(roll)));
            sauces.add(new Cranberry(new Honey(new Nutella(roll))));
        }
        Random rand = new Random();

        // Pick a random sauce combination based on the number of sauces requested by the customer
        switch (num) {
            case 0:
                return roll;
            case 1:
                int rand1 = rand.nextInt(3);
                return sauces.get(rand1);
            case 2:
                int rand2 = rand.nextInt(3) + 3;
                return sauces.get(rand2);
            case 3:
                return sauces.get(6);

        }
        return roll;
    }

}
