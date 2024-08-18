package unsw.business;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import unsw.business.composite.AndRule;
import unsw.business.composite.Constant;
import unsw.business.composite.GreaterThan;
import unsw.business.composite.Lookup;
import unsw.business.composite.NotBlank;
import unsw.business.composite.OrRule;

public class BusinessRuleMain {
    /**
     * Loads a resource file given a certain path that is relative to resources/ for example
     * `/dungeons/maze.json`. Will add a `/` prefix to path if it's not specified.
     * 
     * @precondiction path exists as a file
     * @param path Relative to resources/ will add an implicit `/` prefix if not given.
     * @return The textual content of the given file.
     * @throws IOException If some other IO exception.
     */
    public static String loadResourceFile(String path) throws IOException {
        if (!path.startsWith("/"))
            path = "/" + path;
        return new String(BusinessRuleMain.class.getResourceAsStream(path).readAllBytes());
    }

    public static BusinessRule generateRule(String inputBusinessRule) {
        JSONObject obj = new JSONObject(inputBusinessRule);
        return generateRule(obj);
    }

    private static BusinessRule generateRule(JSONObject obj) {
        String operator = obj.getString("Operator");


        switch (operator) {
            case "AND":
                JSONArray args = obj.getJSONArray("Args");
                return new AndRule(generateRule(args.getJSONObject(0)),
                        generateRule(args.getJSONObject(1)));
            case "OR":
                JSONArray args2 = obj.getJSONArray("Args");
                return new OrRule(generateRule(args2.getJSONObject(0)),
                        generateRule(args2.getJSONObject(1)));
            case "GREATER THAN":
                JSONArray args3 = obj.getJSONArray("Args");
                return new GreaterThan(generateValue(args3.getJSONObject(0)),
                        generateValue(args3.getJSONObject(1)));
            case "NOT BLANK":
                JSONObject arg = obj.getJSONObject("Arg");
                return new NotBlank(generateValue(arg));
        }

        return null;
    }

    private static BusinessValue generateValue(JSONObject obj) {
        String operator = obj.getString("Operator");
        Object arg = obj.get("Arg");
        switch (operator) {
            case "LOOKUP":
                return new Lookup((String) arg);
            case "CONSTANT":
                return new Constant((int) arg);
        }
        return null;
    }
}
