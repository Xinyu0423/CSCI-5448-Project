import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ZooEmployee {
    protected String name;

    /* Variable to create observable events for PropertyChangeListeners */
    protected PropertyChangeSupport support;

    public ZooEmployee() {
        this.name = "Zippy";
        support = new PropertyChangeSupport(this);
    }

    public ZooEmployee(String name) {
        this.name = name;
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

}

