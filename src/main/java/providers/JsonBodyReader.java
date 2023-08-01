package providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JsonBodyReader<T> implements MessageBodyReader<T> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_JSON_TYPE.getType().equals(mediaType.getType()) &&
                MediaType.APPLICATION_JSON_TYPE.getSubtype().equals(mediaType.getSubtype());
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Gson gson = createGson();
        Reader reader = new InputStreamReader(entityStream, StandardCharsets.UTF_8);
        return gson.fromJson(reader, genericType);
    }

    private Gson createGson() {
        return new GsonBuilder()
                .serializeNulls()
//                .setPrettyPrinting()
                .create();
    }
}