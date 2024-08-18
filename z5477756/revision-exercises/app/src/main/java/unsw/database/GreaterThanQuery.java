package unsw.database;

import java.util.Map;

public class GreaterThanQuery extends Query {
    String lhs;
    Query rhs;

    GreaterThanQuery(String lhs, Query rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public boolean evaluate(Map<String, Object> data) {
        Integer lhsVal = (Integer) data.get(lhs);

        if (rhs instanceof Constant c) {
            Integer rhsVal = (Integer) c.getValue();
            return lhsVal > (rhsVal);
        }

        // ????
        return false;
    }

}
