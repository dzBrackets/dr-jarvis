package DataClass;

import java.io.Serializable;

public class prescriptionsHistory implements Serializable {
    private String presId;
    private String date;
    private String patName;
    private String jsonData ;

    public prescriptionsHistory prescriptionsHistory(String presId, String date, String patName, String jsonData) {
        this.presId = presId;
        this.date = date;
        this.patName = patName;
        this.jsonData = jsonData;
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

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
