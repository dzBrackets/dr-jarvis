package Controler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewDrug implements Initializable {

    public TextField name_TXF;
    public TextField type_TXF;
    public TextField doss_TXF;
    public JFXTextArea write_TXA;
    public JFXButton add_btn;
    public JFXComboBox weight_combo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> weight_list = FXCollections.observableArrayList("mg","kg");
        weight_combo.setItems(weight_list);
    }

  

    public void add_drug(ActionEvent actionEvent) {
    }
}
