package unsw.business;

import java.util.List;
import java.util.Map;

public interface BusinessRule {
    public boolean evaluate(Map<String, Object> values);

    public List<BusinessRule> getArgs();
}
