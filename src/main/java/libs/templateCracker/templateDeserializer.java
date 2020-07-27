package libs.templateCracker;

import DataClass.Patient;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class templateDeserializer {
public templateController tc;
    public templateDeserializer(List<Holder> components){
        tc=new templateController();
       tc.container.setMinHeight(842);
        tc.container.setMinWidth(595.0);
        tc.edge.setLayoutX(25.0);
         tc.edge.setLayoutY(14.0);
         tc.edge.setMinHeight(522.0);
        tc.container.getChildren().add(tc.edge);
       for (Holder holder:components) {
           tc.edge.getChildren().add(HolderToNodeTransformer(holder));
       }
   }
Node HolderToNodeTransformer(Holder holder){
       Node babyNodyLoveU = null;
    if(holder.getType().equals(Holder.IMAGE)){
        babyNodyLoveU=new ImageView();

        nodeFiller(babyNodyLoveU,holder);
        Image img = new Image(holder.getValue());
        ((ImageView) babyNodyLoveU).setImage(img);
    }
    else if(holder.getType().equals(Holder.LABEL)){
        babyNodyLoveU=new Label(holder.getValue());
        nodeFiller(babyNodyLoveU,holder);
        switch (holder.getName()){
            case "FirstName":
                tc.p_first_name= (Label) babyNodyLoveU;
                break;
            case "LastName":
                tc.p_last_name= (Label) babyNodyLoveU;
                break;
            case "Age":
                tc.p_age= (Label) babyNodyLoveU;
                break;
            case "Date":
                tc.p_date= (Label) babyNodyLoveU;
                break;
        }
    }
   else if(holder.getType().equals(Holder.DRUGS_HOLD)){

        babyNodyLoveU=new GridPane();

        nodeFiller(babyNodyLoveU,holder);
        tc.drug_list= (GridPane) babyNodyLoveU;
   babyNodyLoveU.setId(Holder.DRUGS_HOLD);
   }
   else if(holder.getType().equals(Holder.PATIENT_HOLDER)){
        babyNodyLoveU=new GridPane();
        nodeFiller(babyNodyLoveU,holder);
        tc.patientInfo= (GridPane) babyNodyLoveU;
        tc.patientInfo.setHgap(20);
babyNodyLoveU.setId(Holder.PATIENT_HOLDER);
    }
return babyNodyLoveU;
}
void nodeFiller(Node babyNodyLoveU, Holder holder){
    babyNodyLoveU.setId(holder.getName());
    babyNodyLoveU.setLayoutX(holder.getXPos());
    babyNodyLoveU.setLayoutY(holder.getYPos());
    babyNodyLoveU.prefWidth(holder.getWidth());
    babyNodyLoveU.prefHeight(holder.getHeight());
    babyNodyLoveU.setStyle(holder.style);
}

}
