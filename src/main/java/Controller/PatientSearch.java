package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import libs.requestFormer;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import static dr.FinalsVal.requestP;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    requestFormer<Patient> req2=new requestFormer<>();
    @FXML
     private JFXTextField search_TF;
     @FXML
     private Pane serach_panel;
     @FXML
     private JFXButton add_btn;
     Patient selectedPatient=null;
    List <String> data=new ArrayList<>();
    IntegerProperty i=new SimpleIntegerProperty(1);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



        /*
        req.onReceive(c-> {
            System.out.println("hello");
            if (value.length() > 0 && req.respL!=null){
                data=req.respL.stream().map(Patient::getFullName).collect(Collectors.toList());
TextFields.bindAutoCompletion(search_TF,data);
            }
        });


        search_TF.textProperty().addListener(v->{
            acb.dispose();
            value=((StringProperty)v).getValue();
                requestP.offer(req.querySearch("SELECT *","WHERE firstName $LIKE '"+value+"%' OR lastName $LIKE '"+value+"%'",5));

        });
        */

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

            System.out.println("not found you have to handle this");
        }


    }
    public void initializePane() throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/quick_panel.fxml"));
         root = loader.load();
        control=loader.getController();
    }
    public void open_quick_pane() throws IOException {
       initializePane();
     /*    control.setName_label();*/
        quick_scene =new Scene(root);
        quick_scene.setFill(Color.TRANSPARENT);
        quick_scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        quick_stage =new Stage();
        quick_stage.initModality(Modality.APPLICATION_MODAL);
        quick_stage.setScene(quick_scene);
        quick_stage.initStyle(StageStyle.TRANSPARENT);
        quick_stage.show();
        quick_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                quick_stage.close();
                MainPanelC.effect.setRadius(0);
            }
        });

        quick_scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset=event.getSceneX();
                yOffset=event.getSceneY();
            }
        });
        quick_scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                quick_stage.setX(event.getScreenX() - xOffset);
                quick_stage.setY(event.getScreenY()-yOffset);
            }
        });


    }
    public  void close_search_pane(){
        MainPanelC.search_stage.close();
    }
}
