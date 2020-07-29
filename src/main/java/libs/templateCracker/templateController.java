package libs.templateCracker;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class templateController {
    public GridPane drug_list,patientInfo;
    public Label p_first_name;
    public Label p_date;
    public Label p_last_name;
    public Label p_age;
    public final Pane container;
    public final Pane edge;
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

}
