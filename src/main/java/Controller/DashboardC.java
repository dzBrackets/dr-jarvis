package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardC implements Initializable {


    public AnchorPane dashborad_pane;
    public Pane recent_pane;
    public JFXButton shwo_all_btn;
    public Pane boxes_pane;
    public Label doctor_name_label;
    public Label date_label;
    public Label all_prec_cpt;
    public Label drug_cpt;
    public Label patient_cpt;
    public Label today_precp_cpt;
    public Pane recent_pane1;
    public JFXButton shwo_all_btn1;
    public Button item1;
    public Label item1_name_label;
    public JFXButton show_pres_btn;
    public Label item1_age;
    public Label item1_diago;
    public Label item1_time;
    public Button item2;
    public Label item2_name_label1;
    public Label item2_age;
    public JFXButton show_pres_btn2;
    public Label item2_diago;
    public Label item2_time;
    public Button item3;
    public Label item1_name_label11;
    public JFXButton show_pres_btn3;
    public Label item3_age;
    public Label item3_diago;
    public Label item3_time;
    public Pane pane1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void show_prescription(ActionEvent actionEvent) {
    }

    public void show_all_pres(ActionEvent actionEvent) {
    }
}
