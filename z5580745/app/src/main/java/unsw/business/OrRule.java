package unsw.business;

import java.util.List;
import java.util.Map;

public class OrRule implements BusinessRule{

    private List<BusinessRule> args;

    public OrRule(List<BusinessRule> args){
        this.args = args;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        boolean result = false;
        for (BusinessRule rule: args){
            result = result || rule.evaluate(values);
        }

        return result;
    }

    @Override
    public List<BusinessRule> getArgs() {
        return args;
    }
    
}
