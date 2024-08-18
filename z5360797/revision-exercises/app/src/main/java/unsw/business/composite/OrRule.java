package unsw.business.composite;

import java.util.Map;
import unsw.business.BusinessRule;

public class OrRule implements BusinessRule {

    private BusinessRule rule1;
    private BusinessRule rule2;

    public OrRule(BusinessRule rule1, BusinessRule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;

    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        return rule1.evaluate(values) || rule2.evaluate(values);
    }

}
