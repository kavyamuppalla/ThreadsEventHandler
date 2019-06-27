import java.util.Map;

public interface Tracker {

    void push(String message);

    Boolean has(String message);

    public Map<String, Integer> tracker();

    void handle(String message, EventHandler e);
}
