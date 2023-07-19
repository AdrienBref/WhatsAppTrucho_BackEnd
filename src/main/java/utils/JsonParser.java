package utils;
import com.google.gson.Gson;

public class JsonParser {

    public <T> T parse(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
