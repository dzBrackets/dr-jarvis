package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.cellController;
import libs.requestFormer;
import model.popUpWindow;
import model.showButton;
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
    static public  Stage patientFormStage;
    public static Stage Table_quick_stage ;
    public  static  quick_panelC control ;
    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
    public JFXButton add_patient_btn;
    public Label info2_label;
    private requestFormer<Patient> req=formerP;
    double xOffset,yOffset;
    Parent root ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventTrigger();
        initCol();
        loadData();
    }
    public void initCol(){
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
                            requestP.offer(req.remove(patient_table.getItems().get(cellController.index)));
                        write_TXF.clear();

                    }
                    if(prop.getValue()==2){
                        //open new prescription
                        try {
                            initializePane();
                            open_quick_pane();
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

    static public void closePopuUp(){
        patientFormStage.close();
    }

    public void add_patient_table(ActionEvent actionEvent) throws IOException, InterruptedException {



        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_patient.fxml"));
        Scene sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        patientFormStage =new Stage();
        patientFormStage.initModality(Modality.APPLICATION_MODAL);
        patientFormStage.setScene(sc);
        patientFormStage.initStyle(StageStyle.TRANSPARENT);
        patientFormStage.show();

    }
    public void initializePane() throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/quick_panel.fxml"));
        root = loader.load();
        control=loader.getController();

    }
    public void open_quick_pane() throws IOException {
        initializePane();
        Scene   quick_scene =new Scene(root);
        quick_scene.setFill(Color.TRANSPARENT);
        quick_scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Table_quick_stage =new Stage();
        Table_quick_stage .initModality(Modality.APPLICATION_MODAL);
        Table_quick_stage .setScene(quick_scene);
        Table_quick_stage .initStyle(StageStyle.TRANSPARENT);
        //setLabels
   //     control.setInfoLabelValues(selectedPatient);//
        Table_quick_stage .show();
        MainPanelC.effect.setRadius(3.25);
        Table_quick_stage .setOnCloseRequest(event -> {
            Table_quick_stage .close();
            MainPanelC.effect.setRadius(0);
        });

        quick_scene.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        quick_scene.setOnMouseDragged(event -> {
            Table_quick_stage .setX(event.getScreenX() - xOffset);
            Table_quick_stage .setY(event.getScreenY()-yOffset);
        });


    }
}
