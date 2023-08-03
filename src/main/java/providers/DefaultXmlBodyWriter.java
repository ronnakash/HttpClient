package providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import creators.Creator;

public class DefaultXmlBodyWriter<T> extends XmlBodyWriter<T> {

    @Override
    public ObjectMapper createXmlMapper() {
        return Creator.defaultWriterObjectMapper();
    }
}
