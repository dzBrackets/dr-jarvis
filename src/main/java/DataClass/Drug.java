package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class Drug implements Serializable {
    private  String code="N/D";
    private String name="N/D";
    private String notice="N/D";
    private ArrayList<String> type=new ArrayList<>();
    private ArrayList<String> dose=new ArrayList<>();

    public static final long serialVersionUID=1L;

    public Drug Drug(String code, String name, String[] type, String[] dose){
this.name=name;
this.code=code;
Collections.addAll(this.dose,dose);
Collections.addAll(this.type,type);
        return this;
    }
    public Drug Drug(String code, String name, String type, String dose,String notice){
        this.name=name;
        this.code=code;
        (this.type=new ArrayList<String>()).add(type);
        (this.dose=new ArrayList<String>()).add(dose);
        this.notice=notice;

        return this;
    }
    public Drug Drug(String code, String name, ArrayList<String> type, ArrayList<String> dose){
        this.name=name;
        this.code=code;
        this.type=type;
        this.dose=dose;

        return this;
    }

    public void appendDose(String dose){
        this.dose.add(dose);
    }
    public void appendType(String type){
        this.type.add(type);
    }
    public void removeDose(int dose){

    }
    public String toString()
    {

        return "Drug [name="
                + name
                + ", type="
                + type
                + ", Dose="
                + dose
                + ", code="
                + code + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return code.equals(drug.code)  &&
                name.equals(drug.name) &&
                type.equals(drug.type) &&
                dose.equals(drug.dose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, type, dose);
    }

    public String getCode() {
        return code;
    }

    @JsonIgnore
    public Drug getThis() {
        return this;
    }
    @JsonIgnore

    public void setNumCode(int  code) {
        setCode(String.format("#%06d",code));

    }
    public void setCode(String  code) {
            this.code=code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public void setType(ArrayList<String> type) {
        this.type = type;
    }

    public ArrayList<String> getDose() {
        return dose;
    }

    public void setDose(ArrayList<String> dose) {
        this.dose = dose;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
