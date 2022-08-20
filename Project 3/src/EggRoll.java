public class EggRoll implements Roll {
    // Base type stays the same when extras are added to the roll
    private String baseType;
    // Name updates with extras added
    private String name;

    public EggRoll() {
        name = "Egg Roll";
        baseType = "Egg Roll";
    }

    public String rollInfo() {
        return name;
    }

    public double cost() {
        return 2.5;
    }

    public String getName() {
        return name;
    }

    public String getBaseType() {
        return baseType;
    }
}
