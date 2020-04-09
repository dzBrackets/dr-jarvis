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

public class prescription {
    String name;
    String type;
    String doss;
    String qts;
    String notice;
    MenuButton  del_btn;

    public prescription(String name, String type, String doss, String qts, String notice, MenuButton del_btn) {
        this.name = name;
        this.type = type;
        this.doss = doss;
        this.qts = qts;
        this.notice = notice;
        this.del_btn = del_btn;

        ImageView img1 = new ImageView("dr/image/trash_24px.png");
        ImageView img2 = new ImageView("dr/image/ball_point_pen_24px.png");
        ImageView img3 =new ImageView("dr/image/menu_vertical_24px.png");
        img1.setFitHeight(15);  img1.setFitWidth(15);
        img2.setFitWidth(15); img2.setFitHeight(15);
        MenuItem delete = new MenuItem("Delete...", img1);
        MenuItem edit = new MenuItem("Edit...", img2);
        del_btn.getItems().addAll(delete,edit);
        del_btn.setText("");
        del_btn.setGraphic(img3);
        del_btn.setPopupSide(Side.LEFT);
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

    public MenuButton getDel_btn() {
        return del_btn;
    }

    public void setDel_btn(MenuButton del_btn) {
        this.del_btn = del_btn;
    }

}
