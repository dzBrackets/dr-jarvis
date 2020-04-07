package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class prescription {
    String name;
    String type;
    String doss;
    String qts;
    String notice;
    SplitMenuButton  del_btn;

    public prescription(String name, String type, String doss, String qts, String notice, SplitMenuButton del_btn) {
        this.name = name;
        this.type = type;
        this.doss = doss;
        this.qts = qts;
        this.notice = notice;
        this.del_btn = del_btn;
    /*    del_btn.getStyleClass().add("type_combo");
        ImageView img1 = new ImageView("dr/image/trash_24px.png");
        ImageView img2 = new ImageView("dr/image/ball_point_pen_24px.png");
        img1.setFitHeight(15);  img1.setFitWidth(15);
        img2.setFitWidth(15); img2.setFitHeight(15);
        JFXButton remove_btn =new JFXButton();
        JFXButton edit_btn =new JFXButton();
        remove_btn.setGraphic(img1);
        edit_btn.setGraphic(img2);
        ObservableList<JFXButton> btn_list= FXCollections.observableArrayList(remove_btn,edit_btn);
        del_btn.setItems(btn_list);*/
        del_btn.getStyleClass().add("");



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

    public SplitMenuButton getDel_btn() {
        return del_btn;
    }

    public void setDel_btn(SplitMenuButton del_btn) {
        this.del_btn = del_btn;
    }

}
