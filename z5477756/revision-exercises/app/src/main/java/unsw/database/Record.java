package unsw.database;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Record {

    private Map<String, Object> data;

    public Record(String rowString, String[] headers, Map<String, Column> columns) {
        data = new HashMap<>();

        String[] rowData = rowString.split("\\|");

        for (int i = 0; i < rowData.length; i++) {
            String col = headers[i];
            String val = rowData[i].trim();

            if (columns.get(col).getType().equals(Column.ColumnType.MARK)) {
                Integer int_val = 0;
                if (!val.equals("")) {
                    int_val = Integer.parseInt(val);
                }
                data.put(col, int_val);
            } else {
                data.put(col, val);
            }

        }

    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public boolean queryMatch(String columnName, Object value) {

        String fetched = data.get(columnName).toString();
        String expected = value.toString();

        return fetched.equals(expected);

    }

    public boolean queryMatchComplex(Query query) {
        return query.evaluate(this.data);
    }

    public void updateEntry(String columnName, Object value) {
        data.put(columnName, value);
    }

    public void setDerivedColumn(String columnName,
            Function<Map<String, Object>, Object> compute) {
        data.put(columnName, compute.apply(this.data));
    }

}
