package model;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.SplitMenuButton;

public class drug {
    private  String name ,type,doss,code;
    private JFXButton notice;
    private SplitMenuButton menu ;

    public drug(String name, String type, String doss, String code, JFXButton notice, SplitMenuButton menu) {
        this.name = name;
        this.type = type;
        this.doss = doss;
        this.code = code;
        this.notice = notice;
        this.menu = menu;
        notice.setText("Show");
        notice.getStyleClass().add("show_btn");
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JFXButton getNotice() {
        return notice;
    }

    public void setNotice(JFXButton notice) {
        this.notice = notice;
    }

    public SplitMenuButton getMenu() {
        return menu;
    }

    public void setMenu(SplitMenuButton menu) {
        this.menu = menu;
    }



}
