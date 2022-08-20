public class JellyRoll implements Roll {
    // Base type stays the same when extras are added to the roll
    private String baseType;
    // Name updates with extras added
    private String name;

    public JellyRoll() {
        name = "Jelly Roll";
        baseType = "Jelly Roll";
    }

    public String rollInfo() {
        return name;
    }

    public double cost() {
        return 2.25;
    }

    public String getName() {
        return name;
    }

    public String getBaseType() {
        return baseType;
    }
}
