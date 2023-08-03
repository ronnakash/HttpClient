package providers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import creators.Creator;

public class DefaultXmlBodyReader<T> extends XmlBodyReader<T> {

    public DefaultXmlBodyReader() {}

    @Override
    public ObjectMapper createXmlMapper() {
        return Creator.defaultReaderObjectMapper();
    }

}
