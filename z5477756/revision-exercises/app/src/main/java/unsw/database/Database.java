package unsw.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import unsw.database.Column.ColumnType;
import unsw.database.Record;

public class Database {

    List<Record> records = new ArrayList<>();
    Map<String, Column> columns;
    List<String> derived_columns = new ArrayList<>();

    Map<String, Function<Map<String, Object>, Object>> derived_column_formulae;

    Map<String, List<String>> dependents; // dependents[a] = things that depend on a

    public Database(List<Column> columns) {

        this.columns = new HashMap<>();
        this.dependents = new HashMap<>();
        this.derived_column_formulae = new HashMap<>();

        for (Column col : columns) {
            this.columns.put(col.getName(), col);
        }

    }

    // Query is an empty class that you can do whatever you want to (add
    // subclasses/functions/whatever)
    // the only requirement is that the name remains the same.
    public Query parseQuery(String query) {
        // wrapped in an array list to allow us to remove tokens from the "stream"
        // you don't have to change this function.
        return parseOrExpr(new ArrayList<>(Arrays.asList(query.split("\\s"))));
    }

    // Queries database using already compiled Query
    // If a record matches twice you can add it twice (i.e. you don't have to handle
    // distinctly)
    public List<Map<String, Object>> queryComplex(Query query) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Record r : records) {
            if (r.queryMatchComplex(query)) {
                list.add(r.getData());
            }
        }

        return list;
        // TODO: ^^
    }

    // Gets the column type for the specified column name
    public ColumnType getColumn(String name) {
        Column col = columns.get(name);

        if (col == null) {
            return null;
        }
        return col.getType();
        // TODO: ^^
    }

    // should return number of new records inserted
    public int ingest(String contents) {
        // split up into rows
        List<String> rows = new ArrayList<>(Arrays.asList(contents.split("\n")));

        // grab the first row for schema
        // NOTE: When splitting on certain characters in java you need to escape them
        // (this is due to split actually taking in a regex).
        // So if you need to split on `|` you'll want to do `\\|` instead as per below.
        // (you shouldn't need to split on anything else other than newlines as above)
        String[] header = rows.remove(0).split("\\|");

        // trim schema to remove surrounding whitespace
        for (int i = 0; i < header.length; i++)
            header[i] = header[i].trim();

        // == end of starter code ==
        // TODO: Finish off the rest of this method

        for (String row : rows) {

            Record r = new Record(row, header, columns);
            for (String s : derived_columns) {
                r.setDerivedColumn(s, derived_column_formulae.get(s));
            }

            this.records.add(r);

        }

        return rows.size();
    }

    // Queries database for all records where columnName has a value that .equals()
    // value.
    public List<Map<String, Object>> querySimple(String columnName, Object value) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Record r : records) {
            if (r.queryMatch(columnName, value)) {
                list.add(r.getData());
            }
        }

        return list;
        // TODO: ^^
    }

    public void updateData(String queryColumnName, Object queryValue, String columnName, Object columnValue) {
        boolean updated = false;

        for (Record r : records) {

            if (r.queryMatch(queryColumnName, queryValue)) {
                r.updateEntry(columnName, columnValue);
                updated = true;
            }
        }

        if (updated) {
            List<String> dependents_of_updated = dependents.getOrDefault(columnName, new ArrayList<>());
            for (String dependent : dependents_of_updated) {
                for (Record r : records) {
                    r.setDerivedColumn(dependent, derived_column_formulae.get(dependent));
                }
            }
        }

        return;
        // TODO: ^^
    }

    public void addDerivedColumn(String columnName, List<String> dependencies,
            Function<Map<String, Object>, Object> compute) {

        derived_columns.add(columnName);
        derived_column_formulae.put(columnName, compute);

        for (String dependency : dependencies) {
            if (dependents.get(dependency) == null) {
                dependents.put(dependency, new ArrayList<>());
            }
            dependents.get(dependency).add(columnName);

        }

        for (Record r : records) {
            r.setDerivedColumn(columnName, compute);
        }
    }

    /*
     * For the following functions you'll want to change them a very tiny amount,
     * you will probably
     * be changing the return types and making it so it constructs objects in this
     * said recursive manner.
     * 
     * To make it simple, the query language presumes all input is valid and doesn't
     * support `()` to decide precedence.
     * 
     * As a very rough explanation of how this works (it's an exam, you do *NOT*
     * need to understand the specifics just
     * focus on changing the return new Query()'s to what you need to construct the
     * query object).
     * 
     * If you are REALLY struggling look at the practice exam, how did you do the
     * query structure for business rules there?
     * How, can you apply that structure to this question in a similar fashion...
     */

    public Query parseAtom(List<String> tokens) {
        if (tokens.size() == 0) {
            return null;
        }

        String tok = tokens.remove(0);
        try {
            // Integer constant
            int result = Integer.parseInt(tok);

            return new IntegerConstant(result);
            // return new Query();
            // TODO: ^^
        } catch (NumberFormatException e) {
            // (ignore)
        }

        // then it must be a String
        // we may have to combine multiple tokens into ones
        String agg = tok.substring(1);
        if (agg.charAt(agg.length() - 1) == '\'') {
            // A string constant.
            String result = agg.substring(0, agg.length()-1);

            return new StringConstant(result);
            // TODO: ^^
        }

        // this is where the text has spaces i.e. 'a b c', what we do is recombine the
        // tokens
        // until we find one with a ' terminator, this isn't a great strategy, but it's
        // simple!
        // this presumes we'll terminate, again we always presume valid input!
        while (true) {
            String next = tokens.remove(0);

            if (next.charAt(next.length() - 1) == '\'') {
                // A string constant.
                String result = agg + " " + next.substring(0, next.length() - 1);
                return new StringConstant(agg);

                // return new Query();
            } else {
                agg += " " + next;
            }
        }
    }

    public Query parseOperatorExpr(List<String> tokens) {
        if (tokens.size() == 0) {
            return null;
        }

        // we presume we always need at least one operator and since
        // columns can't have boolean values we always need a symbol

        // lhs is the column name
        String lhs = tokens.remove(0);
        // the symbol (i.e. = or >)
        String op = tokens.remove(0);
        // what to compare it to i.e. 'A' or 2
        Query rhs = parseAtom(tokens);

        if (op.equals("=")) {
            return new EqualQuery(lhs, rhs);

        }
        return new GreaterThanQuery(lhs, rhs);

        // TODO: ^^
    }

    public Query parseAndExpr(List<String> tokens) {
        if (tokens.size() == 0) {
            return null;
        }

        // lhs
        Query lhs = parseOperatorExpr(tokens);

        // read AND
        if (tokens.size() >= 1 && tokens.get(0).equals("AND") && lhs != null) {
            tokens.remove(0);
            // recurse i.e. a AND b AND c => a AND (b AND c)
            Query rhs = parseAndExpr(tokens);

            // you should do something with the results of above...
            // something like X x = new X(lhs, rhs);
            return new ANDQuery(lhs, rhs);
            // TODO:^
        } else {
            return lhs;
        }
    }

    public Query parseOrExpr(List<String> tokens) {
        if (tokens.size() == 0) {
            return null;
        }

        // lhs
        Query lhs = parseAndExpr(tokens);

        // read OR
        if (tokens.size() >= 1 && tokens.get(0).equals("OR") && lhs != null) {
            tokens.remove(0);
            // recurse i.e. a OR b OR c => a OR (b OR c)
            Query rhs = parseOrExpr(tokens);

            // you should do something with the results of above...
            // something like X x = new X(lhs, rhs);
            return new ORQuery(lhs, rhs);
            // TODO:^
        } else {
            return lhs;
        }
    }
}
