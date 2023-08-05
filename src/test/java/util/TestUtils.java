package util;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static NestedObject makeBody() {
        List<LeafObject> leafObjects = Arrays.asList(new LeafObject(1), new LeafObject(2), new LeafObject(3));
        InnerObject innerObject = new InnerObject(leafObjects);
        List<InnerObject> innerObjects = Arrays.asList(innerObject, innerObject, innerObject);
        return new NestedObject(innerObjects);
    }

}
