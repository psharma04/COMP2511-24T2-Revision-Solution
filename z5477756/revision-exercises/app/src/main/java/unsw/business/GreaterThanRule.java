package unsw.business;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class GreaterThanRule implements BusinessRule {

    // Required to be greater than target.
    String key;
    Object target;

    GreaterThanRule(JSONObject rule) {
        JSONArray args = rule.getJSONArray("Args");

        for (int i = 0; i < args.length(); i++) {
            JSONObject arg = args.getJSONObject(i);
            String operator = arg.getString("Operator");

            if (operator.equals("LOOKUP")) {
                key = arg.getString("Arg");
            } else if (operator.equals("CONSTANT")) {
                target = arg.get("Arg");
            }
        }
    }

    private double cast_to_double(Object val) throws ClassCastException {
        try {
            return (double) val;
        } catch (Exception e) {

            return (double) (int) val;

        }
    }

    public boolean evaluate(Map<String, Object> values) {
        double a = cast_to_double(values.get(key));
        double b = cast_to_double(target);

        return a > b;

    }
}
