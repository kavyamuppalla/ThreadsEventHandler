import java.util.HashMap;
import java.util.Map;

public class EventTracker implements Tracker {

    private static EventTracker INSTANCE = new EventTracker();

    private Map<String, Integer> tracker;

    private EventTracker() {
        this.tracker = new HashMap<>();
    }
    @Override
    public Map<String, Integer> tracker() {
        return tracker;
    }

    synchronized public static EventTracker getInstance() {
        return INSTANCE;
    }

    synchronized public void push(String message) {
        Integer count = tracker.get(message);
        if (count == null) {
            tracker.put(message, 1);
        }
        else {
            tracker.put(message, count + 1);
        }


    }

    synchronized public Boolean has(String message) {
        Integer count = tracker.get(message);
        if(tracker.containsKey(message) && count >  0) {
            return true;
        }
        return false;
    }

    synchronized public void handle(String message, EventHandler e) {
        e.handle();
        Integer count = tracker.get(message);
        if (count == null) {
            tracker.put(message, 1);
        }
        else {
            tracker.put(message, count - 1);
        }

    }

    // Do not use this. This constructor is for tests only
    // Using it breaks the singleton class
    EventTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }
}
