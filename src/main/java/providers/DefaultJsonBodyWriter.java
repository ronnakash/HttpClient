package providers;

import com.google.gson.Gson;

public class DefaultJsonBodyWriter<T> extends JsonBodyWriter<T> {

    public DefaultJsonBodyWriter() {
        super(new Gson());
    }
}
