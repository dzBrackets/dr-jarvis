package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class usedDrug {
    String name;
    String type;
    String doss;
    String qts;
    String notice;

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
