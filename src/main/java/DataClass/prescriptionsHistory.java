package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import libs.coronaDb.DrugDeserializer;
import model.usedDrug;

import java.io.Serializable;
import java.util.List;

public class prescriptionsHistory implements Serializable {
    private String presId;
    private String date;
    private List<usedDrug> drugList=null;
    private String userId="N/D";

    public prescriptionsHistory prescriptionsHistory(String presId, String date,List<usedDrug> drugList,String userId) {
        this.presId = presId;
        this.date = date;
        this.drugList = drugList;
        this.userId=userId;
        return this;
    }

    @JsonIgnore
    public void setUUID(int  code) {
        setPresId(String.format("#%06d",code));

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


    public List<usedDrug> getDrugList() {
        return drugList;
    }
    @JsonDeserialize(using = DrugDeserializer.class)
    public void setDrugList(List<usedDrug> drugList) {
        this.drugList = drugList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
