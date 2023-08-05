package util;

import java.util.Objects;

public class LeafObject {
    private int value;

    public LeafObject(int value) {
        this.value = value;
    }

    public LeafObject() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LeafObject other = (LeafObject) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
