package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientSearch implements Initializable {
    public Label search_label;
    public JFXButton cancel_btn;
    @FXML
     private JFXTextField search_TF;
     @FXML
     private Pane serach_panel;
     @FXML
     private JFXButton add_btn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [] possible_list= {"aymen", "brahim", "daouadji","team","dzb"};
        TextFields.bindAutoCompletion(search_TF,possible_list);

    }
    public void exit_methode(ActionEvent actionEvent) {
        MainPanelC.search_stage.close();
        MainPanelC.effect.setRadius(0);
    }
    public void add_methode(ActionEvent actionEvent) {

    }
}
