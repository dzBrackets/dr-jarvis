package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import libs.cellController;
import libs.requestFormer;
import model.components.amazingDialog;
import model.components.animations;
import model.components.spawnButton;
import model.popUpWindow;
import model.showButton;
import model.stageLoader;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static dr.FinalsVal.requestH;
import static dr.Main.mainStage;
import static libs.helper.cleanDelete;
import static libs.helper.movableFalse;

public class patientDetailsC implements Initializable {
    public TableView<prescriptionsHistory> table;
    public TableColumn<prescriptionsHistory,String> date;
    public TableColumn<prescriptionsHistory,String>  id;
    public TableColumn<prescriptionsHistory,String>  show;
    public Label name_label;
    public AnchorPane quick_pane;
    public Label age_label;
    public Label visite_label;
    public Label lastDiago;
    public JFXButton exit_btn;
    public JFXButton add_button;
    public JFXButton delete_btn;
    public Pane container;
    patientDetailsC detailController=null;
    Patient selectedPatient=null;
    popUpWindow window=null;
    prescriptionDetailsC presDetailController=null;
    public libs.cellController<prescriptionsHistory> cellController=new cellController<>();
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        movableFalse(table);
initEvent();
table.setFocusTraversable(false);
    }

    private void initEvent() {
        cellController.clicked.addListener(v->{
            initPresStage();

        });
    }
    public void initPresStage(){

        stageLoader op=new stageLoader("View prescription","/dr/FXML/POPUP/prescriptionDetails.fxml");
        presDetailController = (prescriptionDetailsC) op.getController();
        op.show();
        presDetailController.loadFrom(table.getItems().get(cellController.index));
        presDetailController.dad(op.getStage());
    }
    public void initCol(){
        date.getStyleClass().add("start");
        show.getStyleClass().add("end");
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        id.setCellValueFactory(new PropertyValueFactory<>("presId"));
        show.setCellFactory(cellController.BCellFactory(new showButton("Details ...")));
    }
    public void loadFrom(Patient patient){
setPatientInfo(patient);
getPresList(patient);
    }

    private void getPresList(Patient patient) {
        requestFormer<prescriptionsHistory> req=new requestFormer<>();
        req.onReceive(v->{
            Platform.runLater(()->table.getItems().setAll(req.respond));
        });
        requestH.offer(req.mojoJojo("getPresId", patient.getPrescriptionsId().toArray(String[]::new)));
    }

    private void setPatientInfo(Patient patient){
         selectedPatient = patient;
        name_label.setText(patient.getFullName());
        age_label.setText(patient.getAge()+"");
        visite_label.setText(patient.getLastVisit());
        lastDiago.setText(patient.getLastDiagnostic());
    }

    public void pops() {
         window = new popUpWindow(quick_pane);
        window.show(mainStage,mainStage.getX()+500,mainStage.getY()+53);

    }
    popUpWindow getPops(){return window;}
    public void exit_methode(ActionEvent actionEvent) {
        animations easeOut = new animations(quick_pane, animations.EASE_OUT);
        easeOut.after(x->getPops().cleanHide());
        easeOut.playAnimation();
    }

    public void add_methode(ActionEvent actionEvent) {
    }

    public void delete_methode(ActionEvent actionEvent) {

    }
                        
}
