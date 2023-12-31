package providers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public abstract class JsonBodyWriter<T> implements MessageBodyWriter<T> {

    private final Gson gson;

    protected JsonBodyWriter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_JSON_TYPE.getType().equals(mediaType.getType()) &&
                MediaType.APPLICATION_JSON_TYPE.getSubtype().equals(mediaType.getSubtype());
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonElement element = gson.toJsonTree(t);

        try (ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream()) {
            try (OutputStreamWriter writer = new OutputStreamWriter(tempOutputStream, StandardCharsets.UTF_8)) {
                gson.toJson(element, writer);
                writer.flush();
            }

            byte[] jsonData = tempOutputStream.toByteArray();
            entityStream.write(jsonData);
        }
    }


}
