package libs.templateCracker;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class Deserializer {


   static public void deserialize(templateController tc, List<Holder> components){

        for (Holder holder:components) {
            tc.edge.getChildren().add(HolderToNodeTransformer(holder,tc));
        }

    }
static Node HolderToNodeTransformer(Holder holder,templateController tc)  {
       Node babyNodyLoveU = null;
    if(holder.getType().equals(Holder.IMAGE)){
        babyNodyLoveU=new ImageView();
        nodeFiller(babyNodyLoveU,holder);

        Image img = null;
        try {
            img = new Image(String.valueOf(new File(holder.getValue()).toURI().toURL()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
static void nodeFiller(Node babyNodyLoveU, Holder holder){

    babyNodyLoveU.setId(holder.getName());
    babyNodyLoveU.setLayoutX(holder.getXPos());
    babyNodyLoveU.setLayoutY(holder.getYPos());
    babyNodyLoveU.setStyle(holder.style);

    if(babyNodyLoveU instanceof Region){
        ((Region) babyNodyLoveU).setPrefHeight(holder.getHeight());
        ((Region) babyNodyLoveU).setPrefWidth(holder.getWidth());
    }
    else if(babyNodyLoveU instanceof ImageView){
            ((ImageView) babyNodyLoveU).setFitHeight(holder.getHeight());
            ((ImageView) babyNodyLoveU).setFitWidth(holder.getWidth());
    }
    else{
    babyNodyLoveU.minWidth(holder.getWidth());
    babyNodyLoveU.maxHeight(holder.getHeight());
    }

}

}
