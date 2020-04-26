package Controller;

import DataClass.Drug;
import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import libs.requestFormer;
import model.popupMenu;
import model.prescription;
import model.showButton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static dr.FinalsVal.requestD;

public class quick_panelC implements Initializable {
static public String fName="N/D",age="N/D",lastDiagnostic="N/D",lastVisit="N/D";
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
    private JFXTextField drug_search;
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
List<Drug> drugList=new ArrayList<>();
    Drug selectedDrug=null;
    Patient selectedPatient=null;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
        setInfoLabelValues();
        initSearchBar();
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
        table.setItems(data);
    }

    public void exit_methode(ActionEvent actionEvent) {
        if(PatientList.Table_quick_stage!=null&&PatientList.Table_quick_stage.isShowing()){
            PatientList.Table_quick_stage.close();
        }
        else {
            PatientSearch.quick_stage.close();
        }

         MainPanelC.effect.setRadius(0);
    }




    public void save(ActionEvent actionEvent) {
    }

    public void save_and_print(ActionEvent actionEvent) {
    }

    public void add_to_table(ActionEvent actionEvent) {
    }

    public void fillDrugInfo(Drug d){
        drug_search.setText(d.getName());
        type_combo.getItems().setAll(d.getType());
        type_combo.getSelectionModel().select(0);
        doss_combo.getItems().setAll(d.getDose());
        doss_combo.getSelectionModel().select(0);
    }

    public  void setInfoLabelValues(String fName, int age, String lastVisit, String lastDiagnostic) {
        name_label.setText(fName);
        age_label.setText(""+age);
        last_notice_label.setText(lastDiagnostic);
        visite_label.setText(lastVisit);
    }
    public  void setInfoLabelValues(Patient patient) {
        selectedPatient=patient;
        name_label.setText(patient.getFullName());
        age_label.setText(""+patient.getAge());
        last_notice_label.setText(patient.getLastDiagnostic());
        visite_label.setText(patient.getLastVisit());
    }
    public void setInfoLabelValues(){
        name_label.setText(fName);
        age_label.setText(age);
        last_notice_label.setText(lastDiagnostic);
        visite_label.setText(lastVisit);
    }


    public void initEvents(){
    add_btn.setOnAction(v->{
            if(selectedDrug!=null){
                data.add(new prescription(selectedDrug.getName(),type_combo.getSelectionModel().getSelectedItem(),doss_combo.getSelectionModel().getSelectedItem(),"3",notice_text_field.getText()));
                drugList.add(selectedDrug);
                selectedDrug=null;
                type_combo.getItems().clear();
                doss_combo.getItems().clear();
                drug_search.setText("");

            }
        });
    cellController.MenuDispatcher.addListener(v-> {
        IntegerProperty prop = (IntegerProperty) v;
        if (prop.getValue() == 0) {
            System.out.println("delete");
        data.remove(cellController.index);
        drugList.remove(cellController.index);
        }
        if (prop.getValue()==1){
            fillDrugInfo(drugList.get(cellController.index));
            data.remove(cellController.index);
            drugList.remove(cellController.index);
        }
    });
    }

    public void initSearchBar(){
        requestFormer<Drug> req=new requestFormer<>();
        popupMenu suggestionsBar =new popupMenu();
        InvalidationListener k=v -> {
            String value = ((StringProperty) v).getValue();
            if (value.length() > 0)
                requestD.offer(req.querySearch("SELECT *", "WHERE name $LIKE '" + value + "%'", 5));
            else suggestionsBar.onHide();
        };
        suggestionsBar.bind(drug_search);
        req.onReceive(c-> {
            List<String> data=req.respond.stream().map(Drug::getName).collect(Collectors.toList());
            Platform.runLater(() ->{
                if ( data.size()>0){
                    suggestionsBar.setItem(data);
                    suggestionsBar.showSuggestion();
                }
                else suggestionsBar.onHide();
            });
        });

        suggestionsBar.onSelect(v->{
            int value=((IntegerProperty) v).getValue();
            if(value!=-1&&req.respond.size()-1>=value){
                selectedDrug=req.respond.get(value);
                drug_search.textProperty().removeListener(k);
                //setting info
                fillDrugInfo(selectedDrug);
                suggestionsBar.onHide();
                initSearchBar();

            }
        });

        drug_search.textProperty().addListener(k);

    }

}
