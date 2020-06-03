package Controller;

import DataClass.Drug;
import DataClass.Patient;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.components.drugItem;

import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.customAttrs;

public class templateC implements Initializable {
    public GridPane drug_list;
    public AnchorPane container;
    public Label p_first_name;
    public Label p_date;
    public Label p_last_name;
    public Label p_age;
    public Label SIDEBOX_text1;
    public Label SIDEBOX_text2;
    public Label SIDEBOX_text3;
    public Label SIDEBOX_text4;
    // static GridPane sharedList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setDoctorLocalInfo
    }
    void setDoctorLocalInfo(){
        SIDEBOX_text1.setText(customAttrs.getAttribute("tbl1"));
        SIDEBOX_text2.setText(customAttrs.getAttribute("tbl2"));
        SIDEBOX_text3.setText(customAttrs.getAttribute("tbl3"));
        SIDEBOX_text4.setText(customAttrs.getAttribute("tbl4"));

    }
    public void reset(){
        drug_list.getChildren().clear();
        setTemplateInfo(new Patient());
    }
    public void setTemplateInfo(Patient patient) {
        p_age.setText(patient.getAge()+"");
        p_date.setText(patient.getLastVisit());
        p_first_name.setText(patient.getFirstName());
        p_last_name.setText(patient.getLastName());
    }


}
