package model;

import com.jfoenix.controls.JFXButton;

public class prescription {
    String name,type,doss,qts,notice;
    JFXButton del_btn;


    public prescription(String name, String type, String doss, String qts, String notice,JFXButton del_btn){
        this.name=name;
        this.type=type;
        this.doss=doss;
        this.qts=qts;
        this.notice=notice;
        this.del_btn=del_btn;
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
    public JFXButton getDel_btn() {
        return del_btn;
    }

    public void setDel_btn(JFXButton del_btn) {
        this.del_btn = del_btn;
    }



}
