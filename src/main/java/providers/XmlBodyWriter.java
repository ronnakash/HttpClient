package providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import creators.XmlMapperCreator;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public abstract class XmlBodyWriter<T> implements MessageBodyWriter<T>, XmlMapperCreator {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MediaType.APPLICATION_XML_TYPE.getType().equals(mediaType.getType()) &&
                MediaType.APPLICATION_XML_TYPE.getSubtype().equals(mediaType.getSubtype());
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        ObjectMapper xmlMapper = createXmlMapper();
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(entityStream, StandardCharsets.UTF_8);
            xmlMapper.writeValue(writer, t);
        } finally {
            if (writer != null) {
                writer.flush();
            }
        }
    }
}
