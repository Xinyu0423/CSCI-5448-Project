import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ZooAnnouncer extends ZooEmployee implements PropertyChangeListener {
    private boolean atZoo = false;
    private int zooEmployeeCount = 0;

    public ZooAnnouncer() {
        super();
    }

    public ZooAnnouncer(String name) {
        super(name);
    }

    public void arrive(int day) {
        System.out.println("Zoo Announcer arrives to the Zoo on Day " + day + ".");
        atZoo = true;
    }

    public void leave(int day) {
        System.out.println("There are no more messages for the Zoo Announcer to announce for Day " + day + ", so they leave the Zoo.");
        atZoo = false;
    }

    // Announcer observes other Zoo Employees and announces their actions
    public void propertyChange(PropertyChangeEvent evt) {
        // Keep track of when employees arrive to the zoo
        if (evt.getPropertyName().equals("arrive")) {
            // Have the ZooAnnouncer arrive with the first employee each day
            if (zooEmployeeCount == 0) {
                arrive((int) evt.getNewValue());
            }
            zooEmployeeCount += 1;
        }
        // Keep track of when employees leave the zoo
        else if (evt.getPropertyName().equals("leave")) {
            zooEmployeeCount -= 1;
            // Have the ZooAnnouncer only leave when all the other employees have left
            if (zooEmployeeCount == 0) {
                leave((int) evt.getNewValue());
            }
        }
        // Only send announcements if the announcer is actually at the zoo
        else if (atZoo) {
            System.out.println("Hi, this is the Zoo Announcer. The " + evt.getPropertyName() + " is about to " + evt.getNewValue() + "!");
        }
    }
}
