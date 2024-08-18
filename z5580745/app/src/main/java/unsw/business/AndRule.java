package unsw.business;

import java.util.List;
import java.util.Map;

public class AndRule implements BusinessRule{

    private List<BusinessRule> args;

    public AndRule(List<BusinessRule> args){
        this.args = args;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        boolean result = true;
        for (BusinessRule rule: args){
            result = result && rule.evaluate(values);
        }

        return result;
    }

    public List<BusinessRule> getArgs() {
        return args;
    }
    
}
