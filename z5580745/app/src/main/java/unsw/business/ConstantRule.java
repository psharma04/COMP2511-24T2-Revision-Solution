package unsw.business;

import java.util.List;
import java.util.Map;

public class ConstantRule implements VariableRule{

    private List<BusinessRule> args;
    private Object value;

    public ConstantRule(List<BusinessRule> args){
        this.args = args;
    }

    public ConstantRule(Object value){
        this.value = value;
    }

    @Override
    public boolean evaluate(Map<String, Object> values) {
        Object arg1 = args.get(0).getArgs().get(0);

        if (arg1 instanceof Number || arg1 instanceof Boolean){
            return true;
        }
        if (arg1 instanceof String){
            String a1 = (String) arg1;
            return !a1.isBlank();
        }
        return false;
    }

    @Override
    public List<BusinessRule> getArgs() {
        return args;
    }

    @Override
    public Object getValue(Map<String, Object> values) {
        return value;
    }
    
}
