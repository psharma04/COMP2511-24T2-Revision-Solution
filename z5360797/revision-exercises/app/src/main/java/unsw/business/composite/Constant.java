package unsw.business.composite;

import java.util.Map;
import unsw.business.BusinessValue;

public class Constant implements BusinessValue {
    private int key;

    public Constant(int key) {
        this.key = key;
    }

    @Override
    public Object evaluate(Map<String, Object> values) {
        return key;
    }

}
