package unsw.database;

import java.util.Map;

public abstract class Query {

    public abstract boolean evaluate(Map<String, Object> data);
    // you are to implement this function.
}
