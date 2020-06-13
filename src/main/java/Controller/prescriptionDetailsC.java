package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import libs.cellController;
import libs.helper;
import libs.printerException;
import libs.requestFormer;
import model.components.dialog;
import model.components.spawnButton;
import model.popUpWindow;
import model.showButton;
import model.usedDrug;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

public class prescriptionDetailsC implements Initializable {
    public Label name_label;
    public Label age_label;
    public Label visite_label;
    public Label last_notice_label;
    public Label prec_id;
    public Label user_id;
    public Label date_label;
    public JFXButton exit_btn;
    public AnchorPane quick_pane;
    public JFXButton print_btn;
    public JFXButton delete_btn;
    public Label lastDiago;
    @FXML
    private TableView<usedDrug> table;
    @FXML
    private TableColumn<usedDrug, String> name_colm;
    @FXML
    private TableColumn<usedDrug, String> type_colm;
    @FXML
    private TableColumn<usedDrug, String> doss_colm;
    public static popUpWindow  show ;

    @FXML
    private TableColumn<usedDrug, String> qts_colm;
    @FXML
    private TableColumn<usedDrug, String> notice_colm;
    private final requestFormer<Patient> req=new requestFormer<>();
    prescriptionsHistory selectedPres=null;
    Patient selectedPatient=null;
    ObservableList<usedDrug> data= FXCollections.observableArrayList();
    libs.cellController<usedDrug> cellController=new cellController<>();
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        loadData();
        eventTrigger();

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


private void setPatientInfo(Patient patient){
        selectedPatient=patient;
        name_label.setText(patient.getFullName());
        age_label.setText(patient.getAge()+"");
        visite_label.setText(patient.getLastVisit());
    lastDiago.setText(patient.getLastDiagnostic());
}
void eventTrigger(){
    cellController.clicked.addListener(v->{
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/show_window.fxml"));
        try {
            Parent root = loader.load();
            show_winC control=loader.getController();
            show = new popUpWindow(root.getChildrenUnmodifiable());
            show.show(quick_pane.getScene().getWindow());
            control.value_area.setText(table.getItems().get(cellController.index).getNotice());

        } catch (IOException e) {
            e.printStackTrace();
        }
    });

}
void dad(Stage st){
        this.stage=st;
}
    public void exit_methode(ActionEvent actionEvent) {
        stage.close();
    }

    public void loadFrom(prescriptionsHistory prescriptionsHistory) {
        selectedPres=prescriptionsHistory;
        date_label.setText(prescriptionsHistory.getDate());
        prec_id.setText(prescriptionsHistory.getPresId());
        user_id.setText(prescriptionsHistory.getUserId());
        data.addAll(prescriptionsHistory.getDrugList());
        last_notice_label.setText(prescriptionsHistory.getDiagnosis());

        req.onReceive(v->{
            try{
            Patient selectedPatient = req.respond.get(0);
                Platform.runLater(()-> setPatientInfo(selectedPatient));

            }
            catch (IndexOutOfBoundsException e){
                Platform.runLater(()-> {
                    System.out.println("error");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("something went wrong.!!");
                    alert.setHeaderText("the patient in prescription not found!!");
                    alert.setContentText("you want to delete the Prescription?");

                    ButtonType show = new ButtonType("continue anyway");
                    alert.getButtonTypes().add(show);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                    requestH.offer(formerH.remove(prescriptionsHistory));
                    exit_btn.fire();
                    }
                    if (result.get() == ButtonType.CANCEL) {
                        exit_btn.fire();
                    }
                });
            }
        });
      //  requestP.offer(req.find("getId",prescriptionsHistory.getUserId()));
        requestP.offer(req.querySearch("SELECT *", "WHERE patientId = '" + prescriptionsHistory.getUserId()+"'", 1));


    }

    public void delete_methode(ActionEvent actionEvent) {


        dialog check=new dialog();
        check.setTitle("confirm you choice.");
        check.setContent("you sure you want to delete this prescription?");
        check.setImage(dialog.WARNING);
        JFXButton ok = spawnButton.red("Delete");
        JFXButton cancel = spawnButton.gray("Cancel");
        check.setPosition(300,300);
        check.show(quick_pane.getScene().getWindow(),quick_pane);
        check.getButtonList().setAll(ok,cancel);
        ok.setOnAction(v->{
            requestH.offer(formerH.remove(selectedPres));
            selectedPatient.getPrescriptionsId().remove(selectedPres.getPresId());
            requestP.offer(formerP.update());
            cancel.fire();
            exit_btn.fire();

        });

        cancel.setOnAction(v->{
            check.removeBlur(quick_pane);
            check.close();

        });




    }

    public void print_methode(ActionEvent actionEvent) {
        try {
            helper.printWithData(selectedPatient,selectedPres.getDrugList());
        } catch (printerException e) {
            dialog alr=new dialog();
            alr.setPosition(300,300);
            alr.setTitle("Something went wrong. ");
            alr.setContent("Error:"+e.getMessage()+"\nadd a printer to your system then try again.");
            JFXButton continueBtn = spawnButton.gray("Continue");
            alr.getButtonList().addAll(continueBtn);
            continueBtn.setOnAction(v->{alr.close();exit_btn.fire();});
            alr.show(quick_pane.getScene().getWindow(),quick_pane);
        }

    }
}
