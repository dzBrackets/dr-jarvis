package Controller;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.cellController;
import model.showButton;
import model.usedDrug;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class prescriptionDetailsC implements Initializable {
    public Label name_label;
    public Label age_label;
    public Label visite_label;
    public Label last_notice_label;
    public Label prec_id;
    public Label user_id;
    public Label date_label;
    public JFXButton exit_btn;
    @FXML
    private TableView<usedDrug> table;
    @FXML
    private TableColumn<usedDrug, String> name_colm;
    @FXML
    private TableColumn<usedDrug, String> type_colm;
    @FXML
    private TableColumn<usedDrug, String> doss_colm;
    @FXML
    private TableColumn<usedDrug, String> qts_colm;
    @FXML
    private TableColumn<usedDrug, String> notice_colm;
    ObservableList<usedDrug> data= FXCollections.observableArrayList();;
    libs.cellController<usedDrug> cellController=new cellController<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();

    }
    public void initCol(){
        name_colm.getStyleClass().add("start");
        notice_colm.getStyleClass().add("end");
        name_colm.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_colm.setCellValueFactory(new PropertyValueFactory<>("type"));
        doss_colm.setCellValueFactory(new PropertyValueFactory<>("doss"));
        qts_colm.setCellValueFactory(new PropertyValueFactory<>("qts"));
        notice_colm.setCellFactory(cellController.BCellFactory(new showButton("show")));
    }
    public void  loadData(){
        table.setItems(data);
    }




    public void exit_methode(ActionEvent actionEvent) {
    }
}
