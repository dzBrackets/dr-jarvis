package Controller;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class settingC implements Initializable {
    public Label tab_type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
