// High level interface for the rolls
public interface Roll {
    // Info about the roll
    public String rollInfo();

    // Cost of the roll
    public abstract double cost();

    // Roll name
    public String getName();

    // Base type of the roll
    public String getBaseType();
}


