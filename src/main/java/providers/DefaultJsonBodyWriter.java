package providers;

import com.google.gson.Gson;

public class DefaultJsonBodyWriter<T> extends JsonBodyWriter<T> {

    @Override
    public Gson createGson() {
        return new Gson();
    }
}
