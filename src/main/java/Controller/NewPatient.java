package Controller;

import DataClass.Drug;
import DataClass.Patient;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import libs.requestFormer;

import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Controller.PatientList.setTableItems;
import static Controller.PatientList.closePopuUp;
import static dr.FinalsVal.*;

public class NewPatient implements Initializable {

    public AnchorPane Pform;
    public JFXTextArea write_TXA;
    public JFXButton add_btn;
    private requestFormer<Patient> req=formerP;
    public JFXTextField firstN_TXF;
    public JFXTextField lastN_TXF;
    public JFXDatePicker date;
    @FXML
    ToggleGroup gender_group;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        form_validation();
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
                  closePopuUp();
                }

    }
      catch (InterruptedException | IOException e){

      }
    }

    public void form_validation(){
        firstN_TXF.setFocusTraversable(false);
        lastN_TXF.setFocusTraversable(false);
        RequiredFieldValidator Fname_req =new RequiredFieldValidator();
        RequiredFieldValidator Lname_req =new RequiredFieldValidator();
        RequiredFieldValidator Date_req =new RequiredFieldValidator();
        Fname_req.setMessage("Please fill out this Field !");
        Lname_req.setMessage("Please fill out this Field !");
        Date_req.setMessage("Please choose a date !");
      /*  img1.setFitWidth(20);img1.setFitHeight(20);*/
        Fname_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        Lname_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        Date_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        /*************************************************/
        RegexValidator Fname_val =new RegexValidator();
        RegexValidator Lname_val =new RegexValidator();
        Fname_val.setMessage("Please provide a Valid input !");
        Lname_val.setMessage("Please provide a Valid input !");
        Fname_val.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        Lname_val.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        Fname_val.setRegexPattern("[a-z]{1,10}");
        Lname_val.setRegexPattern("[a-z]{1,10}");

        /*************************************************/
        firstN_TXF.getValidators().addAll(Fname_req,Fname_val);
        lastN_TXF.getValidators().addAll(Lname_req,Fname_val);
        date.getValidators().add(Date_req);
        firstN_TXF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==false){
                    firstN_TXF.validate();
                }}});
        lastN_TXF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==false){
                    lastN_TXF.validate();
                }}});
        date.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==false){
                    date.validate();
                }}});


    }
}
