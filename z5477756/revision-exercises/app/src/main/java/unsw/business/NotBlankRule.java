package unsw.business;

import java.util.Map;

import org.json.JSONObject;

public class NotBlankRule implements BusinessRule {
    String key;

    NotBlankRule(JSONObject rule) {
        JSONObject arg = rule.getJSONObject("Arg");

        key = arg.getString("Arg");
    }

    public boolean evaluate(Map<String, Object> values) {

        return values.get(key) != null;
    }
}
