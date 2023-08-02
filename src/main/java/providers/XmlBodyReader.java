package providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import creators.XmlMapperCreator;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public abstract class XmlBodyReader<T> implements MessageBodyReader<T>, XmlMapperCreator {


    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_XML_TYPE.isCompatible(mediaType);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        ObjectMapper objectMapper = createXmlMapper();
        Reader reader = new InputStreamReader(entityStream, StandardCharsets.UTF_8);
        return objectMapper.readValue(reader, type);
    }

//    protected abstract ObjectMapper createXmlMapper();

}
