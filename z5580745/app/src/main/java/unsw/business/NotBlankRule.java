package unsw.business;

import java.util.List;
import java.util.Map;

public class NotBlankRule implements BusinessRule{

    private List<BusinessRule> args;

    public NotBlankRule(List<BusinessRule> args){
        this.args = args;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        

        VariableRule a1 = (VariableRule) args.get(0);

        Object arg1 = a1.getValue(values);

        if (arg1 instanceof Number || arg1 instanceof Boolean){
            return true;
        }
        if (arg1 instanceof String){
            String str = (String) arg1;
            return !str.isBlank();
        }
        
        return false;
    }

    @Override
    public List<BusinessRule> getArgs() {
        return args;
    }
    
}
