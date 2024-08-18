package unsw.business;

import java.util.Map;

public interface BusinessValue {
    public Object evaluate(Map<String, Object> values);
}
