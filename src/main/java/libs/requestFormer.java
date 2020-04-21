package libs;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

final public class requestFormer<type> {
   public String request;
    public Object canon;
   public String req;
public type[] arguments;
public String functionName;
public Object[] funArguments;
public Object arg1,arg2,ar3,ar4;
    public type obr1,obr2;
    private final IntegerProperty asynk=new SimpleIntegerProperty(1);


    public static final String GET="g";
    public static final String POST="p";
    public static final String UPDATE="u";
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
        System.out.println("you receive an event!!!");

        asynk.addListener(event);
    }
    public void dispatchEvent(){
        System.out.println("you dispatch an event!!");
        asynk.setValue((asynk.getValue()+1)%2);
    }
}
