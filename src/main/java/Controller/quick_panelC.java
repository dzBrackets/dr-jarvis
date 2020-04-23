package Controller;

import DataClass.Drug;
import DataClass.Patient;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import libs.cellController;
import model.prescription;
import model.showButton;

import java.net.URL;
import java.util.ResourceBundle;

public class quick_panelC implements Initializable {

    public Label name_label;
    public Label age_label;
    public Label visite_label;
    public Label last_notice_label;
    public JFXButton exit_btn;
    public JFXButton save_btn;
    public JFXButton print_btn;
    public JFXComboBox<String> type_combo;
    public JFXComboBox<String> doss_combo;
    public Spinner spinner;
    public JFXTextArea notice_text_field;
    public JFXButton add_btn;
    public AnchorPane quick_pane;
    @FXML
    private TableView<prescription> table;
    @FXML
    private JFXTextField drug_name_input;
    @FXML
    private TableColumn<prescription, String> name_colm;
    @FXML
    private TableColumn<prescription, String> type_colm;
    @FXML
    private TableColumn<prescription, String> doss_colm;
    @FXML
    private TableColumn<prescription, String> qts_colm;
    @FXML
    private TableColumn<prescription, String> notice_colm;
    public TableColumn<prescription, String> delete_colm;
    ObservableList<prescription> data=FXCollections.observableArrayList();;
    cellController<prescription> cellController=new cellController<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
        initEvents();


    }

    public void initCol(){
        name_colm.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_colm.setCellValueFactory(new PropertyValueFactory<>("type"));
        doss_colm.setCellValueFactory(new PropertyValueFactory<>("doss"));
        qts_colm.setCellValueFactory(new PropertyValueFactory<>("qts"));
        notice_colm.setCellFactory(cellController.BCellFactory(new showButton("show")));
        delete_colm.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."}));

    }


    public void  loadData(){


        data.add(new prescription("Cocayin","pills","25mg","3","stop !"));
        data.add(new prescription("sarou5","dwa","siro","1000mg","do it !"));

        table.setItems(data);

    }

    public void exit_methode(ActionEvent actionEvent) {
         MainPanelC.quick_stage.close();
         MainPanelC.effect.setRadius(0);
    }




    public void save(ActionEvent actionEvent) {
    }

    public void save_and_print(ActionEvent actionEvent) {
    }

    public void add_to_table(ActionEvent actionEvent) {
    }
    public void initEvents(){

        add_btn.setOnAction(v->{
            //data.add

        });
    }
}
