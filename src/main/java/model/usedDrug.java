package model;

public class usedDrug {
    String name="Unknown";
    String type="N/D";
    String doss="N/D";
    String qts="N/D";
    String notice="N/D";

    public usedDrug  usedDrug(String name, String type, String doss, String qts, String notice) {
        this.name = name;
        this.type = type;
        this.doss = doss;
        this.qts = qts;
        this.notice = notice;
return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoss() {
        return doss;
    }

    public void setDoss(String doss) {
        this.doss = doss;
    }

    public String getQts() {
        return qts;
    }

    public void setQts(String qts) {
        this.qts = qts;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

}
