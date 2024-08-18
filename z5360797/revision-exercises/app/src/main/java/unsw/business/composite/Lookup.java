package unsw.business.composite;

import java.util.Map;
import unsw.business.BusinessValue;

public class Lookup implements BusinessValue {
    private String key;

    public Lookup(String key) {
        this.key = key;
    }

    @Override
    public Object evaluate(Map<String, Object> values) {
        return values.get(key);
    }
    
}
