package libs;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

final public class requestFormer<type> {
   public String request;

public List<type> respL=null;
public String functionName;
public Object[] funArguments;
public Object arg1,arg2,ar3,ar4;
public type respondObject=null;
public ObservableList<type> respond= FXCollections.observableArrayList();
    public type obr1,obr2;
    InvalidationListener event = observable -> {

    };
    private final IntegerProperty async =new SimpleIntegerProperty(1);


    public static final String GET="g";
    public static final String POST="p";
    public static final String UPDATE="u"
            ;public static final String UPDATE_BY ="ub";
    public static final String REMOVE="r";
    public static final String CALLBACK="cb";
    public static final String FIND="f";
public requestFormer(){}
public <klass> requestFormer<type> callBack(String fName, klass[] ob, Class<klass> className){
        this.functionName=fName;
        this.funArguments=ob;
        this.request=CALLBACK;
        return this;
}

    public <klass> requestFormer<type> callBack(String fName, klass arg1,klass arg2, Class<klass> className){
        this.functionName=fName;
        this.arg1=arg1;
        this.arg2=arg2;
        this.request=CALLBACK;
        return this;
    }

    public requestFormer<type> get(){
        this.request=GET;
        return this;
    }
    public requestFormer<type> post(type arg1){
        this.request=POST;
        this.arg1=arg1;
        return this;
    }
    public requestFormer<type> update(){
        this.request=UPDATE;
        return this;
    }
    public requestFormer<type> update(type object){
        this.request= UPDATE_BY;
        arg1=object;
        return this;
    }

    public requestFormer<type> find(String getter,Object like){
        this.request=FIND;
        this.arg1=getter;
        this.arg2=like;
        return this;
    }
    public requestFormer<type> remove(type arg1){
        this.request=REMOVE;
        this.arg1=arg1;
        return this;
    }

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
    public void reply(type obj){
        respondObject=obj;
    }
    public void reply(List<type> resp){
        respond.setAll(resp);
    }

    public requestFormer<type> querySearch(String s, String s1, int entries) {
        this.functionName="querySearch";
        this.arg1=s;
        this.arg2=s1;
        this.ar3=entries;
        this.request=CALLBACK;
        return this;
    }
}
