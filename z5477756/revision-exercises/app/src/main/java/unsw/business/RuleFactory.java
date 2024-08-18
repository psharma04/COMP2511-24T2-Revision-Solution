package unsw.business;

import org.json.JSONObject;

public class RuleFactory {

    public static BusinessRule createRule(JSONObject rule) {
        String operator = rule.getString("Operator");

        switch (operator) {
            case "OR":
                return new ORRule(rule);
            case "AND":
                return new ANDRule(rule);
            case "NOT BLANK":
                return new NotBlankRule(rule);
            case "GREATER THAN":
                return new GreaterThanRule(rule);
            default:
                break;
        }

        return null;
    }
}
