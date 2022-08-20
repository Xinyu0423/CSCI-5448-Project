import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.*;

public class ZooClock {

    private int hour;
    private int day;

    /* Variable to create observable events for PropertyChangeListeners */
    private PropertyChangeSupport support;
    public ZooClock() {
        hour = 0;
        day = 0;
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setTime(int hour, int day) {
        System.out.println("\nZooClock: The time is now " + hour + ":00 on Day " + day + ".");
        // Announce the current time to any observers \
        support.firePropertyChange("ZooClock", this.hour, hour);
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void countTime(int day, int openTime, int closeTime) {
        // Announce the current day to any observers
        support.firePropertyChange("ZooClock Current Day", this.day, day);
        this.day = day;
        for (int i = openTime; i <= closeTime; i++) {
            setTime(i, day);
        }
    }
}
