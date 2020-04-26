package DataClass;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import libs.coronaDb.deserializeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class prescriptionsHistory implements Serializable {
    private String presId;
    private String date;
    private String patId;
    private List<Drug> drugList=null;

    public prescriptionsHistory prescriptionsHistory(String presId, String date, String patName, List<Drug> drugList) {
        this.presId = presId;
        this.date = date;
        this.patId = patName;
        this.drugList = drugList;
        return this;
    }

    public String getPresId() {
        return presId;
    }

    public void setPresId(String presId) {
        this.presId = presId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }
    @JsonDeserialize(using = deserializeType.class)
    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }
}
