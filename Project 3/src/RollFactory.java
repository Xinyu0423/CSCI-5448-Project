/*
Factory Pattern - Used to Instantiate Rolls
 */
public class RollFactory {
    // Create new roll object based on the specified roll type
    public Roll createRoll(String rollType) {
        if (rollType.equalsIgnoreCase("Spring Roll")) {
            return new SpringRoll();
        } else if (rollType.equalsIgnoreCase("Egg Roll")) {
            return new EggRoll();
        } else if (rollType.equalsIgnoreCase("Pastry Roll")) {
            return new PastryRoll();
        } else if (rollType.equalsIgnoreCase("Sausage Roll")) {
            return new SausageRoll();
        } else if (rollType.equalsIgnoreCase("Jelly Roll")) {
            return new JellyRoll();
        }
        return null;
    }
}
