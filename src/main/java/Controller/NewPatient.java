package Controller;

import DataClass.Drug;
import DataClass.Patient;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import libs.requestFormer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Controller.PatientList.setTableItems;
import static Controller.PatientList.closePopuUp;
import static dr.FinalsVal.*;

public class NewPatient implements Initializable {

    private requestFormer<Patient> req=formerP;
    public TextField firstN_TXF;
    public TextField lastN_TXF;
    public JFXDatePicker date;
    @FXML
    ToggleGroup gender_group;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void add_patient(ActionEvent actionEvent) {
int gender=((JFXRadioButton)gender_group.getSelectedToggle()).getText().equals("male")?0:1;
        String pf=removeSpace(firstN_TXF.getText().toLowerCase());
        String pl=removeSpace(lastN_TXF.getText().toLowerCase());
        Patient patient=null ;
      try{
        if(pf.length()>2)
            if(pl.length()>2)
                if(date.getValue().toString().length()>5){
                    patient= new Patient().Patient("N/D",pf,pl,date.getValue(),gender,LocalDate.now(),"N/D");
                patient.setUUID(database.updateUUID("patient"));

          requestP.put(req.post(patient));
                  setTableItems(respondP.take());
                  closePopuUp();}

    }
      catch (InterruptedException | IOException e){

      }
    }
}
