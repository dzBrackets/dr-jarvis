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
    }

}
