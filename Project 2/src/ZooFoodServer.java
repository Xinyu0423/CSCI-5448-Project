import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ZooFoodServer extends ZooEmployee implements PropertyChangeListener {
    private int currentDay;

    public ZooFoodServer() {
        super();
    }

    public ZooFoodServer(String name) {
        super(name);
    }

    public void arrive() {
        System.out.println("ZooFoodServer arrives to the Zoo on Day " + currentDay + ".");
        support.firePropertyChange("arrive", null, currentDay);
    }

    public void leave() {
        System.out.println("ZooFoodServer has completed all of their tasks for Day " + currentDay + " and leaves the Zoo.");
        support.firePropertyChange("leave", null, currentDay);
    }

    public void makeFood() {
        System.out.println("ZooFoodServer is making food.");
    }

    public void serveFood() {
        support.firePropertyChange("ZooFoodServer", null, "serve food");
        System.out.println("ZooFoodServer is serving the food that was prepared.");
    }

    public void clean() {
        System.out.println("ZooFoodServer is cleaning.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        // Update the current day number
        if (evt.getPropertyName().equals("ZooClock Current Day")) {
            currentDay = (int) evt.getNewValue();
        }
        // React to time changes from the ZooClock
        else if (evt.getPropertyName().equals("ZooClock")) {
            if (evt.getNewValue().equals(8)) {
                arrive();
            } else if (evt.getNewValue().equals(11)) {
                makeFood();
            } else if (evt.getNewValue().equals(12)) {
                serveFood();
            } else if (evt.getNewValue().equals(13)) {
                clean();
            } else if (evt.getNewValue().equals(15)) {
                makeFood();
            } else if (evt.getNewValue().equals(17)) {
                serveFood();
            } else if (evt.getNewValue().equals(18)) {
                clean();
            } else if (evt.getNewValue().equals(20)) {
                leave();
            }
        }
    }
}
