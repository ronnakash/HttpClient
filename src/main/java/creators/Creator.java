package creators;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

public class Creator {

    public static Gson defaultGson() {
        return new Gson();
    }

    public static ObjectMapper defaultWriterObjectMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        return xmlMapper;
    }

    public static ObjectMapper defaultReaderObjectMapper() {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return xmlMapper;
    }

}
