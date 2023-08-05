package util;

import java.util.List;
import java.util.Objects;

public class NestedObject {
    private List<InnerObject> innerObjects;

    public NestedObject(List<InnerObject> innerObjects) {
        this.innerObjects = innerObjects;
    }

    public NestedObject() {
    }

    public List<InnerObject> getInnerObjects() {
        return innerObjects;
    }

    public void setInnerObjects(List<InnerObject> innerObjects) {
        this.innerObjects = innerObjects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NestedObject other = (NestedObject) obj;
        return Objects.equals(innerObjects, other.innerObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(innerObjects);
    }

}