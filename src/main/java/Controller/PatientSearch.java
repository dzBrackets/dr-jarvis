package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import libs.requestFormer;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import static dr.FinalsVal.requestP;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PatientSearch implements Initializable {
    public Label search_label;
    public JFXButton cancel_btn;
    requestFormer<Patient> req=new requestFormer<>();
    requestFormer<Patient> req2=new requestFormer<>();
    @FXML
     private JFXTextField search_TF;
     @FXML
     private Pane serach_panel;
     @FXML
     private JFXButton add_btn;
    List <String> data=new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        req.onReceive(c-> {
                data=req.respond.stream().map(Patient::getFullName).collect(Collectors.toList());
            TextFields.bindAutoCompletion(search_TF, data).setOnAutoCompleted(v->{
                String val=v.getCompletion();
                req2.onReceive(g-> {
                        System.out.println(req2.respondObject);//<-patient object contain all patient info
                    //CloseSearchPan()--->make this Aymen Daoudji
                    //OpenNewPrescriptionPanWithArgument(fullName,age,lastVisit,lastDiagnostic)--->Make it Aymen Daoudji
                });

                System.out.println(val);
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
    public void add_methode(ActionEvent actionEvent) {

    }
}
