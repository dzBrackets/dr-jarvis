package Controller;

import DataClass.Patient;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dr.FinalsVal.customAttrs;
import static libs.helper.colorToRgba;

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
    public VBox sideBox;
    public Pane patientBox;
    public Label text_name;
    public Pane sideBoxPane;
    public GridPane patientInfoGrid;
    // static GridPane sharedList;
    public Color secColor=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setDoctorLocalInfo
      // container.setStyle("-fx-border-color:black; -fx-background-radius: 17px; -fx-border-radius: 17px");;
    }
    void setLabelByPos(int i,String val)
    {

        ((Label)sideBox.getChildren().get(i)).setText(val);


    }
    void setLabelBy(ArrayList<String > list){
        for (int i = 0; i <sideBox.getChildren().size() ; i++) {

            ((Label)sideBox.getChildren().get(i)).setText(list.get(i));
        }
    }

    void setPrimaryColor(Color color){
        sideBoxPane.setStyle("-fx-border-color:"+colorToRgba(color)+"; -fx-background-radius: 17px; -fx-border-radius: 17px");
        text_name.setTextFill(color);
        patientBox.setStyle("-fx-border-color:"+colorToRgba(color)+"; -fx-background-radius: 17px; -fx-border-radius: 17px");

        for (int i = 0; i <sideBox.getChildren().size() ; i++) {
            ((Label)sideBox.getChildren().get(i)).setTextFill(color);
        }

        for (int i = 0; i <patientInfoGrid.getRowCount() ; i++) {
            ((Label)patientInfoGrid.getChildren().get(i)).setTextFill(color);
        }

    }
    void setSecondaryColor(Color color){
        secColor=color;
        for (int i = patientInfoGrid.getRowCount(); i < patientInfoGrid.getRowCount()*patientInfoGrid.getColumnCount(); i++) {
            ((Label)patientInfoGrid.getChildren().get(i)).setTextFill(color);
        }

    }
    void settemplateFromEdit(ArrayList<String> str){
        if(str.get(1).length()>0)
            SIDEBOX_text1.setText(str.get(1));
        if(str.get(1).length()>0)
            SIDEBOX_text2.setText(str.get(1));
        if(str.get(1).length()>0)
            SIDEBOX_text3.setText(str.get(1));
        if(str.get(1).length()>0)
            SIDEBOX_text4.setText(str.get(1));
    }
    void setDoctorLocalInfo(){
        try {
            SIDEBOX_text1.setText(customAttrs.getAttribute("tbl1"));
            SIDEBOX_text2.setText(customAttrs.getAttribute("tbl2"));
            SIDEBOX_text3.setText(customAttrs.getAttribute("tbl3"));
            SIDEBOX_text4.setText(customAttrs.getAttribute("tbl4"));
            setPrimaryColor(Color.web(customAttrs.getAttribute("c1")));
            setSecondaryColor(Color.web(customAttrs.getAttribute("c2")));
        }
        catch (IndexOutOfBoundsException ignored){

        }
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
