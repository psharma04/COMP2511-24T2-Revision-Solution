package unsw.database;

import java.util.Map;

public class IntegerConstant extends Constant {
    Object value;

    IntegerConstant(Integer i) {
        this.value = i;
    }

    public boolean evaluate(Map<String, Object> data) {
        return true;
    }

    public Object getValue() {
        return value;
    }
}
