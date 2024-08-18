package unsw.database;

import java.util.Map;

public class StringConstant extends Constant {
    Object value;

    StringConstant(String s) {
        this.value = s;
    }

    public boolean evaluate(Map<String, Object> data) {
        return true;
    }

    public Object getValue() {
        return value;
    }
}
