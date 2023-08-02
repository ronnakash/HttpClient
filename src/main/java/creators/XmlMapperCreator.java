package creators;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface XmlMapperCreator {

    ObjectMapper createXmlMapper();
}
