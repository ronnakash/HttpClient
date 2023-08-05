package providers;

import com.google.gson.Gson;

public class DefaultJsonBodyReader<T> extends JsonBodyReader<T>{

    public DefaultJsonBodyReader() {
        super(new Gson());
    }

}
