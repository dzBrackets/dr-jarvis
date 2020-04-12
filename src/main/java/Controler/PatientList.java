package Controler;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.patient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientList implements Initializable {
    public TableView<patient> patient_table;
    public TableColumn<patient,String> first_C;
    public TableColumn<patient,String> lastName_C;
    public TableColumn<patient, Integer> age_C;
    public TableColumn<patient,String> lastVisite_C;
    public TableColumn<patient,String> id_C;
    public TableColumn<patient,MenuButton> menu_C;
    public TableColumn<patient,JFXButton> diagnostic_C;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
initCol();
loadData();
    }
    public void initCol(){
        first_C.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastName_C.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        age_C.setCellValueFactory(new PropertyValueFactory<>("age"));
        lastVisite_C.setCellValueFactory(new PropertyValueFactory<>("last_visite"));
        id_C.setCellValueFactory(new PropertyValueFactory<>("id"));
        diagnostic_C.setCellValueFactory(new  PropertyValueFactory<>("diagnostic"));
        menu_C.setCellValueFactory(new PropertyValueFactory<>("menu"));
    }
    public void  loadData(){
        ObservableList<patient> data = FXCollections.observableArrayList();
        data.add( new patient("aymen","daouadji","barah","pubg",21,new JFXButton(),new MenuButton()));

        patient_table.setItems(data);

    }

    public void add_patient_table(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_patient.fxml"));
        Scene sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Stage s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        s.initStyle(StageStyle.TRANSPARENT);
        s.show();
    }
}
