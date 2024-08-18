package unsw.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BusinessRuleMain {
    /**
     * Loads a resource file given a certain path that is relative to resources/
     * for example `/dungeons/maze.json`. Will add a `/` prefix to path if it's not
     * specified.
     * 
     * @precondiction path exists as a file
     * @param path Relative to resources/ will add an implicit `/` prefix if not
     *             given.
     * @return The textual content of the given file.
     * @throws IOException If some other IO exception.
     */
    public static String loadResourceFile(String path) throws IOException {
        if (!path.startsWith("/"))
            path = "/" + path;
        return new String(BusinessRuleMain.class.getResourceAsStream(path).readAllBytes());
    }

    private static BusinessRule generateRuleFromJSON(JSONObject rule){
        String operator = rule.getString("Operator");

        if (operator.equals("AND")){
            JSONArray args = rule.getJSONArray("Args");
            List<BusinessRule> list = new ArrayList<BusinessRule>();
            for (Object arg : args){
                list.add(generateRuleFromJSON((JSONObject)arg));
            }
            return new AndRule(list);
        } else if (operator.equals("OR")){
            JSONArray args = rule.getJSONArray("Args");
            List<BusinessRule> list = new ArrayList<BusinessRule>();
            for (Object arg : args){
                list.add(generateRuleFromJSON((JSONObject)arg));
            }
            return new OrRule(list);
        } else if (operator.equals("GREATER THAN")){
            JSONArray args = rule.getJSONArray("Args");
            List<BusinessRule> list = new ArrayList<BusinessRule>();
            for (Object arg : args){
                list.add(generateRuleFromJSON((JSONObject)arg));
            }
            return new GreaterRule(list);
        } else if (operator.equals("NOT BLANK")){
            JSONObject args = rule.getJSONObject("Arg");
            List<BusinessRule> list = new ArrayList<BusinessRule>();
            list.add(generateRuleFromJSON(args));
            
            return new NotBlankRule(list);
        } else if (operator.equals("LOOKUP")){
            Object value = rule.getString("Arg");
            return new LookUpRule(value);

        } else if (operator.equals("CONSTANT")){
            Object value = rule.getInt("Arg");
            return new ConstantRule(value);

        }
        throw new BusinessRuleException("no");
    }

    public static BusinessRule generateRule(String inputBusinessRule) {
        JSONObject rule = new JSONObject(inputBusinessRule);

        return generateRuleFromJSON(rule);
        
    }
}
