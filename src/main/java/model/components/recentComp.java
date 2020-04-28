package model.components;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import static javafx.scene.control.ContentDisplay.GRAPHIC_ONLY;
import static javafx.scene.paint.Color.WHITE;

public class recentComp extends Button{

    private final Label name,age,diagnosis,time;
    private final JFXButton showMore;


public recentComp(Patient patient){
    name=new Label();
    age=new Label();
    diagnosis=new Label();
    time=new Label();
    showMore=new JFXButton("Show Prescription");

         setContentDisplay(GRAPHIC_ONLY);
     //    setLayoutX(6.0);
      //   setLayoutY(264);
        // prefWidth(243.0);
         //prefHeight(100.0);
         setMnemonicParsing(false);
         setStyle("-fx-background-color: transparent; -fx-background-radius: 15px; -fx-border-radius: 15px;");
         setText("recent");
    setId("item1");


    setChildStyle();



    name.setText("name");
    age.setText("age");
    diagnosis.setText("diagno");
    time.setText("time");

    settexts(patient);


    setGraphic(childPan());

        //   <Label id="recent_diago_label" layoutX="7.0" layoutY="61.0" prefHeight="17.0" prefWidth="59.0" text="diagnostic : " textFill="WHITE" />
        //             <Label id="time_label" fx:id="item2_time" layoutX="164.0" layoutY="70.0" text="Today 12:00 " textFill="WHITE" />
}
public void setChildStyle(){
    name.setLayoutX(7);
    name.setLayoutY(6);
    name.prefHeight(21);
    name.prefWidth(115);
    name.setTextFill(WHITE);

    age.setTextFill(WHITE);
    age.setLayoutX(7);
    age.setLayoutY(38);
    age.prefHeight(17);
    age.prefWidth(55);
    age.setTextFill(WHITE);

    diagnosis.setLayoutX(7);
    diagnosis.setLayoutY(61);
    diagnosis.prefHeight(17);
    diagnosis.prefWidth(59);
    diagnosis.setTextFill(WHITE);

    time.setLayoutX(164);
    time.setLayoutY(70);
    time.setTextFill(WHITE);
    diagnosis.setId("recent_diago_label");
    age.setId("recent_age_label");
    name.setId("recent_name_label");
    time.setId("time_label");
    showMore.setId("show_pres_btn");
    showMore.setLayoutX(132);
    showMore.setLayoutY(4);
    showMore.prefHeight(25);
    showMore.prefWidth(95);
    showMore.setTextFill(WHITE);
}
Pane childPan(){
    Pane p=new Pane();
    p.prefHeight(82);
    p.prefWidth(227);
p.getChildren().addAll(name,age,diagnosis,time,showMore);
return p;
}
void settexts(Patient patient){
    name.setText(patient.getFullName());
    age.setText(patient.getAge()+"");
    diagnosis.setText(patient.getLastDiagnostic());
    time.setText(patient.getLastVisit());
}
}
