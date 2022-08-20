public class Feline extends Animal {

    public Feline() {
        super();
    }

    public Feline(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Feline";
    }

    // Special case method sleep override
    public void sleep() {
        double choice = Math.random() * 100;
        if (choice < 30) {
            System.out.println(getName() + " is not tired, and decides to roam around instead.");
        } else if (choice < 60) {
            System.out.println(getName() + " is not tired, and decides to vocally growl instead.");
        } else {
            System.out.println(getName() + " is tired, and goes to sleep peacefully.");
        }
    }

    public void eat() {
        System.out.println(getName() + " feasts on some prey.");
    }

    public void roam() {
        System.out.println(getName() + " lazily moves around.");
    }
}
