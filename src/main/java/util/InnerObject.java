package util;

import java.util.List;
import java.util.Objects;

public class InnerObject {
    private List<LeafObject> leafObjects;


    public InnerObject(List<LeafObject> leafObjects) {
        this.leafObjects = leafObjects;
    }

    public InnerObject() {
    }


    public List<LeafObject> getLeafObjects() {
        return leafObjects;
    }

    public void setLeafObjects(List<LeafObject> leafObjects) {
        this.leafObjects = leafObjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InnerObject other = (InnerObject) obj;
        return Objects.equals(leafObjects, other.leafObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leafObjects);
    }

}
