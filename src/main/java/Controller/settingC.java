package Controller;

import DataClass.userData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dr.FinalsVal;
import dr.async;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

public class settingC implements Initializable {
    public Label tab_type;
    public JFXButton import_btn;
    public JFXButton export_btn;
    public JFXButton cancel_btn;
    public JFXButton save_btn;
    public JFXTextField name_field;
    public JFXTextField email_field;
    public JFXTextField adr_field;
    public JFXTextField phone_field;
    public GridPane fields_gridpane;
    public AnchorPane setting_pane;
    public Tab personal_tab;
    public JFXButton edit_btn;
    public GridPane labels_gridpane;
    public Label namel_label;
    public Label email_label;
    public Label phone_label;
    public Label adr_label;
    public JFXButton load_file_btn;
    public JFXButton load_api_btn;
    public JFXButton buy_btn;
    public JFXButton clean_btn;
    public Tab Preferences_tab;
    public JFXComboBox timezone;
    public JFXComboBox timeformat;
    public Tab customize_tab;

       static async alpha=new async();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
alpha.onReceive(v->setDocInfo());
    }

     void setDocInfo(){

         namel_label.setText(local_data.getName());
        email_label.setText(local_data.getEmail());
        adr_label.setText(local_data.getAddress());
        phone_label.setText(local_data.getPhone());
    }

    public void personal_selected(Event event) {
        tab_type.setText("Personal ");
    }

    public void preferences_selected(Event event) {
        tab_type.setText("Preferences ");
    }

    public void customize_selected(Event event) {
        tab_type.setText("customize ");
    }

    public void infoTab_selected(Event event) {
        tab_type.setText("info");
    }

    public void edit_doc(ActionEvent actionEvent) {
        edit_btn.setVisible(false);
        save_btn.setVisible(true);
        cancel_btn.setVisible(true);
        labels_gridpane.setVisible(false);
        fields_gridpane.setVisible(true);
        name_field.setText(namel_label.getText());
        email_field.setText(email_label.getText());
        adr_field.setText(adr_label.getText());
        phone_field.setText(phone_label.getText());
    }

    public void cancel_edit(ActionEvent actionEvent) {
        fields_gridpane.setVisible(false);
        labels_gridpane.setVisible(true);
        save_btn.setVisible(false);
        cancel_btn.setVisible(false);
        edit_btn.setVisible(true);
    }

    public void save_edit(ActionEvent actionEvent) {

        namel_label.setText(name_field.getText());
        email_label.setText(email_field.getText());
        adr_label.setText(adr_field.getText());
        phone_label.setText(phone_field.getText());
        local_data.setName(name_field.getText());
        local_data.setEmail(email_field.getText());
        local_data.setAddress(adr_field.getText());
        local_data.setPhone(phone_field.getText());

        save_btn.setVisible(false);
        cancel_btn.setVisible(false);
        edit_btn.setVisible(true);
        fields_gridpane.setVisible(false);
        labels_gridpane.setVisible(true);
        requestU.offer(formerU.update());

    }
}
