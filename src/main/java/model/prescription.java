package model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

public class prescription {
    String name;
    JFXButton del_btn,notice;
    JFXComboBox type,doss ;
    Spinner<Integer> qts ;
    ObservableList<String> type_list= FXCollections.observableArrayList("pills","dwa","siro");
    ObservableList<String> doss_list= FXCollections.observableArrayList("10mg","20mg","250mg");

    public prescription(String name, JFXComboBox type, JFXComboBox doss, Spinner qts, JFXButton notice,JFXButton del_btn){
        this.name=name;
        this.type=type;
        this.doss=doss;
        this.qts=qts;
        this.notice=notice;
        this.del_btn=del_btn;
        ImageView img =new ImageView("dr/image/trash_24px.png");
        del_btn.setGraphic(img);
        notice.setText("Show");
       /* notice.setStyle(" -fx-background-color: #D9D9D9; -fx-border:  solid #D9D9D9; -fx-box-sizing: border-box; -fx-border-radius: 10px;" +
                "");*/
       notice.getStyleClass().add("show_btn");
       del_btn.getStyleClass().add("del_btn");
       type.getStyleClass().add("type_combo");
       doss.getStyleClass().add("type_combo");
       qts.getStyleClass().add("qts_spinner");
       type.setItems(type_list);
       doss.setItems(doss_list);
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 3);
        qts.setValueFactory(valueFactory);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JFXComboBox getType() {
        return type;
    }

    public void setType(JFXComboBox type) {
        this.type = type;
    }

    public JFXComboBox getDoss() {
        return doss;
    }

    public void setDoss(JFXComboBox doss) {
        this.doss = doss;
    }

    public Spinner<Integer> getQts() {
        return qts;
    }

    public void setQts(Spinner<Integer> qts) {
        this.qts = qts;
    }

    public JFXButton getNotice() {
        return notice;
    }

    public void setNotice(JFXButton notice) {
        this.notice = notice;
    }
    public JFXButton getDel_btn() {
        return del_btn;
    }

    public void setDel_btn(JFXButton del_btn) {
        this.del_btn = del_btn;
    }



}
