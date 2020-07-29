package libs.templateCracker;

import DataClass.Patient;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class templateController {
    public GridPane drug_list=null,patientInfo=null;
    public Label p_first_name=null;
    public Label p_date=null;
    public Label p_last_name=null;
    public Label p_age=null;
    public  Pane container;
    public  Pane edge;
    public templateController(){
        container=new Pane();
        edge=new Pane();
        container.setMinHeight(842);
        container.setMinWidth(595.0);
        edge.setLayoutX(25.0);
        edge.setLayoutY(14.0);
        edge.setMinHeight(522.0);
        container.getChildren().add(edge);

    }
    public templateController(List<Holder>holders){
        container=new Pane();
        edge=new Pane();
        container.setMinHeight(842);
        container.setMinWidth(595.0);
        edge.setLayoutX(25.0);
        edge.setLayoutY(14.0);
        edge.setMinHeight(522.0);
        container.getChildren().add(edge);
        new templateDeserializer(this,holders);

    }
    public List<Holder> serialize(){
        return templateSerializer.serialize(edge);
    }
    public templateController deserialize(List<Holder>holders){
        container=new Pane();
        edge=new Pane();
        container.setMinHeight(842);
        container.setMinWidth(595.0);
        edge.setLayoutX(25.0);
        edge.setLayoutY(14.0);
        edge.setMinHeight(522.0);
        container.getChildren().add(edge);
        Deserializer.deserialize(this,holders);
        initialize();
       return this;
    }

    private void initialize() {

    }
    public void setTemplateInfo(Patient patient) {
        if(p_age!=null)
            p_age.setText(patient.getAge()+"");
        if(p_date!=null)
            p_date.setText(patient.getLastVisit());
        if(p_first_name!=null)
            p_first_name.setText(patient.getFirstName());
        if(p_last_name!=null)
            p_last_name.setText(patient.getLastName());
    }

}
