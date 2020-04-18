package Controller;

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
import model.prescription;

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
    public JFXComboBox type_combo;
    public JFXComboBox doss_combo;
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
    public TableColumn<prescription, MenuButton> delete_colm;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
    }

    public void initCol(){
        name_colm.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_colm.setCellValueFactory(new PropertyValueFactory<>("type"));
        doss_colm.setCellValueFactory(new PropertyValueFactory<>("doss"));
        qts_colm.setCellValueFactory(new PropertyValueFactory<>("qts"));
        notice_colm.setCellValueFactory(new PropertyValueFactory<>("notice"));
        delete_colm.setCellValueFactory(new PropertyValueFactory<>("del_btn"));

    }
    public void editablecols(){
        /*  making Rows editable  */
        table.setEditable(true);
        name_colm.setCellFactory(TextFieldTableCell.forTableColumn());
       type_colm.setCellFactory(TextFieldTableCell.forTableColumn());
       doss_colm.setCellFactory(TextFieldTableCell.forTableColumn());
       qts_colm.setCellFactory(TextFieldTableCell.forTableColumn());
        /*making Rows event listener*/
        name_colm.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
        });
        type_colm.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setType(event.getNewValue());
        });
       doss_colm.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setDoss(event.getNewValue());
        });
       qts_colm.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setQts(event.getNewValue());
        });
    }
    public void  loadData(){
        ObservableList<prescription> data = FXCollections.observableArrayList();


        data.add(new prescription("Cocayin","pills","25mg","3","stop !",new MenuButton()));
        data.add(new prescription("sarou5","dwa","siro","1000mg","do it !",new MenuButton()));
        table.setItems(data);

    }

    public void exit_methode(ActionEvent actionEvent) {
         MainPanelC.s.close();
         MainPanelC.effect.setRadius(0);

    }

    public void save(ActionEvent actionEvent) {
    }

    public void save_and_print(ActionEvent actionEvent) {
    }

    public void add_to_table(ActionEvent actionEvent) {
    }
}
