package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXAutoCompleteEvent;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import libs.requestFormer;
import model.cPopupMenu;
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
    @FXML
     private JFXTextField search_TF;
     @FXML
     private Pane serach_panel;
     @FXML
     private JFXButton add_btn;
     int i=0;
    List <String> data=new ArrayList<>();
    String value="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        req.onReceive(c-> {
            System.out.println("hello");
                data=req.respond.stream().map(Patient::getFullName).collect(Collectors.toList());
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
