package libs.coronaDb;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.util.List;

public class QuerySelector<type> extends Query {
    private final coCollection<type> myOwn;

      QuerySelector(coCollection <type> myOwn){
        this.myOwn=myOwn;
    }

    public QuerySelector<type> parser(String parser) throws QueryParseException {
        super.parse(parser);
        return this;
    }


    public QuerySelector<type> setVar(String name,Object value) {
        super.setVariable(name,value);
        return this;
    }

    @SuppressWarnings("unused")
    public QuerySelector<type> setVar(int index,Object value) {
        super.setVariable(index,value);
        return this;
    }
    @SuppressWarnings("unchecked")
    public List<type> collect() throws QueryExecutionException {
        return  this.execute(myOwn).getResults();
    }

}
