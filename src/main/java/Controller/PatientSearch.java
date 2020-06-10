package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.requestFormer;
import model.popupMenu;
import model.stageLoader;

import static Controller.PatientList.patientFormStage;
import static dr.FinalsVal.requestP;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public  class PatientSearch implements Initializable {
    public Label search_label;
    public JFXButton cancel_btn;
    public static Scene quick_scene;
    public  static  Stage quick_stage;
    public double xOffset,yOffset;
    public quick_panelC control ;
    public Parent root;
    requestFormer<Patient> req=new requestFormer<>();
    @FXML
    private JFXTextField search_TF;
    @FXML
    private JFXButton add_btn;
    private InvalidationListener k=null;
    private Patient selectedPatient=null;
    private final popupMenu suggestionsBar=new popupMenu();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        suggestionsBar.bind(search_TF);
        suggestionsBar.onSelect(v->{
    int value=((IntegerProperty) v).getValue();
    if(value!=-1&&req.respond.size()-1>=value){
    selectedPatient=req.respond.get(value);
    search_TF.textProperty().removeListener(k);
    search_TF.setText(selectedPatient.getFullName());
    suggestionsBar.onHide();
    add_btn.fire();

    }
});
        req.onReceive(c-> {
            List <String> data=req.respond.stream().map(Patient::getFullName).collect(Collectors.toList());
            Platform.runLater(() ->{
            if ( data.size()>0){
                 suggestionsBar.setItem(data);
                 suggestionsBar.showSuggestion();
                 }
            else suggestionsBar.onHide();
            });
        });
        k = v -> {
            String value = ((StringProperty) v).getValue();
            if (value.length() > 0)
                requestP.offer(req.querySearch("SELECT *", "WHERE firstName $LIKE '" + value + "%' OR lastName $LIKE '" + value + "%'", 5));
            else suggestionsBar.onHide();
        };
        search_TF.textProperty().addListener(k);

    }
    public void exit_methode(ActionEvent actionEvent) {
        MainPanelC.search_stage.close();
        MainPanelC.effect.setRadius(0);
    }
    public void add_methode(ActionEvent actionEvent) throws IOException {
        close_search_pane();
        if(selectedPatient!=null){
        open_quick_pane();
        }
else{

            Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_patient.fxml"));
            Scene sc =new Scene(root);
            sc.setFill(Color.TRANSPARENT);
            sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
            patientFormStage =new Stage();
            patientFormStage.initModality(Modality.APPLICATION_MODAL);
            patientFormStage.setScene(sc);
            patientFormStage.initStyle(StageStyle.TRANSPARENT);
            patientFormStage.show();
            sc.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset=event.getSceneX();
                    yOffset=event.getSceneY();
                }
            });
            sc.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    patientFormStage.setX(event.getScreenX() - xOffset);
                    patientFormStage.setY(event.getScreenY()-yOffset);
                }
            });
        }


    }

    public void open_quick_pane() throws IOException {
        stageLoader sl=new stageLoader("New prescription","/dr/FXML/POPUP/quick_panel.fxml");
        quick_scene =sl.getScene();

        quick_stage =sl.getStage();
        root=sl.getRoot();
        control= (quick_panelC) sl.getController();

        //setLabels
        control.setInfoLabelValues(selectedPatient);
        quick_stage.show();
        quick_stage.setOnCloseRequest(event -> {
            quick_stage.close();
            MainPanelC.effect.setRadius(0);
        });



    }
    public  void close_search_pane(){
        MainPanelC.search_stage.close();
    }
}


/*
        req2.onReceive(g-> {
                    System.out.println(req2.respondObject);//<-patient object contain all patient info
                     selectedPatient = req2.respondObject;

                    //struggle when try to use control here cuz its not initialized yet its work without it though
                    Platform.runLater(() -> {add_btn.fire();
                        control.setInfoLabelValues(selectedPatient.getFullName(), selectedPatient.getAge(), selectedPatient.getLastVisit(), selectedPatient.getLastDiagnostic());

                    });
                });
        req.onReceive(c-> {
            i.setValue(2);
                data=req.respond.stream().map(Patient::getFullName).collect(Collectors.toList());
            TextFields.bindAutoCompletion(search_TF, data).setOnAutoCompleted(v->{
                String val=v.getCompletion();


                requestP.offer(req2.find("getFullName",val));

            });

        });
        requestP.offer(req.get());
*/