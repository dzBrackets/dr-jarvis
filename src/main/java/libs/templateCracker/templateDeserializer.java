package libs.templateCracker;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class templateDeserializer {
public templateController tc;
public templateDeserializer(){

}
    public templateDeserializer(List<Holder> components){
        tc=new templateController();

       for (Holder holder:components) {
           tc.edge.getChildren().add(HolderToNodeTransformer(holder));
       }
   }
Node HolderToNodeTransformer(Holder holder)  {
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
void nodeFiller(Node babyNodyLoveU, Holder holder){

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
    public List<Holder> templateSerializer(Pane node){
    List<Holder> holders=new ArrayList<>();
        for (Node element:node.getChildren()) {
            holders.add(nodeToHolder(element));
        }
        return holders;
    }
    Holder nodeToHolder(Node node){
        Holder holder=new Holder();
        holder.setType(Holder.LABEL);
        holder.setYPos(node.getLayoutY());
        holder.setXPos(node.getLayoutX());
        holder.setName(node.getId());
        holder.setStyle(node.getStyle());
        if(node instanceof Label){
            holder.setValue(((Label)node).getText());
            holder.setHeight(((Label)node).getHeight());
            holder.setWidth(((Label)node).getWidth());
        }

        else if(node instanceof ImageView){
            holder.setValue(((ImageView)node).getImage().getUrl());
            holder.setHeight(((ImageView)node).getFitHeight());
            holder.setWidth(((ImageView)node).getFitWidth());
        }
        else if(node instanceof GridPane){
            holder.setType(node.getId());
            holder.setHeight(((GridPane)node).getHeight());
            holder.setWidth(((GridPane)node).getWidth());
        }
        return holder;
    }
}
