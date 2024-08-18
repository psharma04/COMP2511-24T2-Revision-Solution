package unsw.business.composite;

import java.util.Map;
import unsw.business.BusinessRule;
import unsw.business.BusinessRuleException;
import unsw.business.BusinessValue;

public class GreaterThan implements BusinessRule {
    private BusinessValue val1;
    private BusinessValue val2;

    public GreaterThan(BusinessValue val1, BusinessValue val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        Object a = val1.evaluate(values);
        Object b = val2.evaluate(values);

        if (!(a instanceof Number) || !(b instanceof Number)) {
            throw new BusinessRuleException("Both args must be numbers");
        }

        return (int) a > (int) b;
    }

}
