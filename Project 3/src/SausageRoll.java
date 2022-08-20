public class SausageRoll implements Roll {
    // Base type stays the same when extras are added to the roll
    private String baseType;
    // Name updates with extras added
    private String name;

    public SausageRoll() {
        name = "Sausage Roll";
        baseType = "Sausage Roll";
    }

    public String rollInfo() {
        return name;
    }

    public double cost() {
        return 3.5;
    }

    public String getName() {
        return name;
    }

    public String getBaseType() {
        return baseType;
    }
}
