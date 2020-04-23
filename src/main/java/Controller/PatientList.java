package Controller;

import DataClass.Drug;
import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import io.reactivex.rxjava3.disposables.Disposable;
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
import model.showButton;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import static dr.FinalsVal.*;
import static dr.Main.patientThread;

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
    static public  Stage s;
    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
    public JFXButton add_patient_btn;
    public Label info2_label;
    private requestFormer<Patient> req=formerP;


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


        cellController.MenuDispatcher.addListener(e-> {
                    IntegerProperty prop= (IntegerProperty) e;
                    if(prop.getValue()==0){
                        System.out.println("delete");
                            requestP.offer(req.remove(patient_table.getItems().get(cellController.index)));
                        write_TXF.clear();

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
        s.close();
    }

    public void add_patient_table(ActionEvent actionEvent) throws IOException, InterruptedException {



        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_patient.fxml"));
        Scene sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
         s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        s.initStyle(StageStyle.TRANSPARENT);
        s.show();

    }
}
