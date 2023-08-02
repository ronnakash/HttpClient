package providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DefaultJsonBodyReader<T> extends JsonBodyReader<T>{

    public DefaultJsonBodyReader() {}

    @Override
    public Gson createGson() {
        return new GsonBuilder()
                .serializeNulls()
//                .setPrettyPrinting()
                .create();
    }
}
