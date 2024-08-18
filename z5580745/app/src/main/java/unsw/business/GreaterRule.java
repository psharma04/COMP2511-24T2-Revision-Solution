package unsw.business;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GreaterRule implements BusinessRule{

    private List<BusinessRule> args;

    public GreaterRule(List<BusinessRule> args){
        this.args = args;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        
        if (args.size() < 2){
            throw new BusinessRuleException("Both arguments have to be numeric");
        }

        VariableRule a1 = (VariableRule) args.get(0);
    VariableRule a2 = (VariableRule) args.get(1);

    Object arg1 = a1.getValue(values);
    Object arg2 = a2.getValue(values);

    if (!(arg1 instanceof Number) || !(arg2 instanceof Number)) {
        throw new BusinessRuleException("Both arguments have to be numeric");
    }

    double num1 = ((Number) arg1).doubleValue();
    double num2 = ((Number) arg2).doubleValue();

    return num1 > num2;
    }

    @Override
    public List<BusinessRule> getArgs() {
        return args;
    }
    
}
