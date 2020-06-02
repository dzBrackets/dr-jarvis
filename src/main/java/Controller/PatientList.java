package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import dr.FinalsVal;
import dr.Main;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.cellController;
import libs.requestFormer;
import model.popUpWindow;
import model.showButton;
import model.stageLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



import static dr.FinalsVal.*;

public class PatientList implements Initializable {
    public TableView  <Patient> patient_table;
    public TableColumn<Patient,String> first_C;
    public TableColumn<Patient,String> lastName_C;
    public TableColumn<Patient, Integer> age_C;
    public TableColumn<Patient,String> lastVisite_C;
    public TableColumn<Patient,String> id_C;
    public TableColumn<Patient,String > menu_C;
    public TableColumn<Patient,String> diagnostic_C;
public cellController<Patient> cellController=new cellController<>();
    static ObservableList<Patient> data = FXCollections.observableArrayList();
    public static popUpWindow  showField ;
    public static Stage Table_quick_stage ;
    public  static  quick_panelC control ;

    static public  Stage patientFormStage;

    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
    public JFXButton add_patient_btn;
    public Label info2_label;
    private requestFormer<Patient> req=formerP;
    private requestFormer<prescriptionsHistory> formerH=new requestFormer<>();
    private requestFormer<prescriptionsHistory> req2= FinalsVal.formerH;

    double xOffset,yOffset;
    Parent root ;
    Patient selectedPatient=null;
    private stageLoader sl;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        eventTrigger();
        initEvent();
        initCol();
        loadData();

    }
    public void initCol(){
        id_C.getStyleClass().add("start");
        menu_C.getStyleClass().add("end");
        first_C.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName_C.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        age_C.setCellValueFactory(new PropertyValueFactory<>("age"));
        lastVisite_C.setCellValueFactory(new PropertyValueFactory<>("lastVisit"));
        id_C.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        diagnostic_C.setCellFactory(cellController.BCellFactory(new showButton("show")));
        menu_C.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png", "dr/image/add_32px.png"},new  String[]{"Delete...","Edit...","new prescription..."}));
    }

    public void  loadData(){

            req.onReceive(v->{
                patient_table.setItems(req.respond);
            });

            requestP.offer(req.get());

    }

    static void setTableItems(List<Patient> items){
        data.setAll(items);
    }

    public void eventTrigger(){

        cellController.clicked.addListener(v->{


            FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/show_window.fxml"));
            try {
                Parent root = loader.load();
                show_winC control=loader.getController();
                showField = new popUpWindow(root.getChildrenUnmodifiable());
                showField.show(Main.staticstage);
                control.value_area.setText(patient_table.getItems().get(cellController.index).getLastDiagnostic());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cellController.MenuDispatcher.addListener(e-> {
                    IntegerProperty prop= (IntegerProperty) e;
                    if(prop.getValue()==0){
                        System.out.println("delete");
                        cleanDelete(patient_table.getItems().get(cellController.index));
                        write_TXF.clear();
                    }
                    if(prop.getValue()==1){

                    loadAddStage(patient_table.getItems().get(cellController.index));
                    }
                    if(prop.getValue()==2){
                        try {
                            initializePane();
                            open_quick_pane(patient_table.getItems().get(cellController.index));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }


                    }
                }
        );
        write_TXF.textProperty().addListener(v->{
           String value=((StringProperty)v).getValue();
            System.out.println(value);
            requestP.offer(req.callBack("querySearch","SELECT *","WHERE firstName $LIKE '"+value+"%' OR lastName $LIKE '"+value+"%'",String.class));

        });
    }
private void initEvent(){
        req2.onReceive(v->{
            requestP.offer(req.remove(selectedPatient));
            selectedPatient=null;
        });
        formerH.onReceive(v->{
            prescriptionsHistory[] blue = formerH.respond.toArray(prescriptionsHistory[]::new);
            Platform.runLater(()->requestH.offer(req2.removeBunch(blue)));
        });
}
    private void cleanDelete(Patient patient) {
         selectedPatient = patient;
            requestH.offer(formerH.mojoJojo("WHERE presId = ", patient.getPrescriptionsId().toArray(String[]::new)));

    }

    static public void closePopuUp(){
        patientFormStage.close();
    }

void loadAddStage(Patient options){
    stageLoader patientLoader = new stageLoader("/dr/FXML/POPUP/New_patient.fxml");
    patientFormStage =patientLoader.getStage();
    if(options!=null) ((NewPatient)patientLoader.getController()).preFilled(options);
    patientLoader.show();


}
    public void add_patient_table(ActionEvent actionEvent) throws IOException, InterruptedException {

loadAddStage(null);
    }
    public void initializePane() throws IOException {
         sl = new stageLoader("/dr/FXML/POPUP/quick_panel.fxml");
        root = sl.getRoot();
        control= (quick_panelC) sl.getController();

    }
    public void open_quick_pane(Patient selectedPatient) throws IOException {
        initializePane();
        Scene   quick_scene =sl.getScene();
        quick_scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Table_quick_stage =sl.getStage();
          control.setInfoLabelValues(selectedPatient);
        sl.show();
        MainPanelC.effect.setRadius(3.25);
        Table_quick_stage .setOnCloseRequest(event -> {
            Table_quick_stage .close();
            MainPanelC.effect.setRadius(0);
        });



    }
}
