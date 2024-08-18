package unsw.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class ORRule implements BusinessRule {
    List<BusinessRule> rules;

    ORRule(JSONObject rule) {
        rules = new ArrayList<>();
        JSONArray args = rule.getJSONArray("Args");

        for (int i = 0; i < args.length(); i++) {
            JSONObject arg = args.getJSONObject(i);
            rules.add(RuleFactory.createRule(arg));
        }

    }

    public boolean evaluate(Map<String, Object> values) {

        for (BusinessRule rule : rules) {
            if (rule.evaluate(values)) {
                return true;
            }
        }

        return false;
    }
}
