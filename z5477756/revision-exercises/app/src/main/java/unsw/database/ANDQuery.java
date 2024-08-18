package unsw.database;

import java.util.Map;

public class ANDQuery extends Query {
    Query q1;
    Query q2;

    ANDQuery(Query q1, Query q2) {
        this.q1 = q1;
        this.q2 = q2;
    }

    public boolean evaluate(Map<String, Object> data) {
        return q1.evaluate(data) && q2.evaluate(data);
    }

}
