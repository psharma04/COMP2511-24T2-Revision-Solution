package unsw.business.composite;

import java.util.Map;
import unsw.business.BusinessRule;
import unsw.business.BusinessValue;

public class NotBlank implements BusinessRule {
    private BusinessValue value;

    public NotBlank(BusinessValue value) {
        this.value = value;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        Object val = value.evaluate(values);
        if (val == null)
            return false;
        if (val instanceof Number) {
            return true;
        } else {
            String valString = (String) val;
            return !valString.isBlank();
        }
    }

}
