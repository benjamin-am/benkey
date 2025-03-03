package persistence;

// Based on the Writable class in the JsonSerializationDemo
import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this object as a JSON object
    JSONObject toJson();

}
