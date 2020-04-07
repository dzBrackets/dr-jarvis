package Controler;

import com.jfoenix.controls.*;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import model.prescription;

import java.net.URL;
import java.util.ResourceBundle;

public class quick_panelC implements Initializable {

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

    public TableColumn<prescription, JFXComboBox> delete_colm;
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

        data.add(new prescription("Cocayin","pills","25mg","3","stop !",new SplitMenuButton()));
        data.add(new prescription("sarou5","dwa","siro","1000mg","do it !",new SplitMenuButton()));
        table.setItems(data);

    }
}
