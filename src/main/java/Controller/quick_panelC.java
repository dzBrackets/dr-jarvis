package Controller;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dr.Main;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import libs.requestFormer;
import model.components.drugItem;
import model.popUpWindow;
import model.popupMenu;
import model.usedDrug;
import model.showButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static dr.FinalsVal.*;

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
    public Spinner<Integer> spinner;
    public JFXTextArea notice_text_field;
    public JFXButton add_btn;
    public AnchorPane quick_pane;

    @FXML
    private TableView<usedDrug> table;
    @FXML
    private JFXTextField drug_search;
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
    public TableColumn<usedDrug, String> delete_colm;
    public static popUpWindow  showField ;
    ObservableList<usedDrug> data=FXCollections.observableArrayList();;
    cellController<usedDrug> cellController=new cellController<>();
List<Drug> drugList=new ArrayList<>();
    Drug selectedDrug=null;
    Patient selectedPatient=null;
    prescriptionsHistory pres;
    private boolean FIRE_EXIT=true;
private boolean added=false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // spinner=new Spinner<Integer>(1,99,1,1);

        initCol();
        loadData();
        setInfoLabelValues();
        initSearchBar();
        initEvents();
        eventTrigger();

    }


    public void initCol(){
        name_colm.getStyleClass().add("start");
        delete_colm.getStyleClass().add("end");
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




    public void save() throws IOException {

         pres = new prescriptionsHistory();
        pres.setUUID(database.updateUUID("prescriptions"));
        pres.setDrugList(table.getItems());
        pres.setUserId(selectedPatient.getPatientId());
        selectedPatient.updateVisit();
        selectedPatient.addPrescription(pres.getPresId());
        pres.setDate(selectedPatient.getLastVisit());
        requestP.offer(formerP.update(selectedPatient));
        requestH.offer(formerH.post(pres));
        local_data.updateDayStats(database.getSize("prescriptions"));
        requestU.offer(formerU.update());
        if(FIRE_EXIT)
        exit_btn.fire();



    }

    public void save_and_print(ActionEvent actionEvent) throws IOException {
        FIRE_EXIT=false;
        save_btn.fire();
        FIRE_EXIT=true;

        MainPanelC.templateController.setTemplateInfo(selectedPatient);
        int  i=0;
        while(i<data.size()){
            MainPanelC.templateController.drug_list.add(new drugItem(data.get(i)),0,i);

            if(i % 5==0){
                print(MainPanelC.templateStatic);
                  MainPanelC.templateController.drug_list.getChildren().clear();}
            i++;

        }
        MainPanelC.templateController.reset();
        exit_btn.fire();

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
            if(!added){
                data.add(new usedDrug().usedDrug(selectedDrug.getName(),type_combo.getSelectionModel().getSelectedItem(),doss_combo.getSelectionModel().getSelectedItem(),spinner.getValue()+"",notice_text_field.getText()));
                drugList.add(selectedDrug);
                added=true;
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
            added=false;

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
            added=false;
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
    public void eventTrigger() {

        cellController.clicked.addListener(v -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/show_window.fxml"));
            try {
                Parent root = loader.load();
                show_winC control = loader.getController();
                showField = new popUpWindow(root.getChildrenUnmodifiable());
                showField.show(quick_pane.getScene().getWindow());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
