package unsw.database;

import java.util.Map;

public class EqualQuery extends Query {
    String lhs;
    Query rhs;

    EqualQuery(String lhs, Query rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public boolean evaluate(Map<String, Object> data) {
        Object lhsVal = data.get(lhs);

        if (rhs instanceof Constant c) {
            Object rhsVal = c.getValue();
            return lhsVal.equals(rhsVal);
        }

        //????
        return false;
    }

}
