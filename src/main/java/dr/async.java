package dr;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

final public class async {
    InvalidationListener event = observable ->{};

    private final IntegerProperty async =new SimpleIntegerProperty(1);
    public void onReceive(InvalidationListener event) {
        System.out.println("you handle an event!!!");
        async.removeListener(this.event);
        async.addListener(event);
        this.event =event;
    }
    public void dispatchEvent(){
        System.out.println("you dispatch an event!!");
        async.setValue((async.getValue()+1)%2);
    }
}
