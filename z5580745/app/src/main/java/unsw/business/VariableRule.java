package unsw.business;

import java.util.List;
import java.util.Map;

public interface VariableRule extends BusinessRule{
    public Object getValue(Map<String, Object> values);
}
