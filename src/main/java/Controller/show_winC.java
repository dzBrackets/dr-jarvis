package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class show_winC implements Initializable {

    public Label win_name;
    public Label value_area;
    public Pane win_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exit_methode(ActionEvent actionEvent) {

        if(DrugList.showNotice!=null&&DrugList.showNotice.isShowing()){
            DrugList.showNotice.hide();
        }
        else if(PatientList.showField!=null&&PatientList.showField.isShowing()){
            PatientList.showField.hide();
    }
    }
}
