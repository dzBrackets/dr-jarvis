package libs.templateCracker;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class templateSerializer {

 static public List<Holder> serialize(Pane node){
        List<Holder> holders=new ArrayList<>();
        for (Node element:node.getChildren()) {
            holders.add(nodeToHolder(element));
        }
        return holders;
    }
   static Holder nodeToHolder(Node node){
       Holder holder=new Holder();
       holder.setYPos(node.getLayoutY());
       holder.setXPos(node.getLayoutX());
       holder.setName(node.getId());
       holder.setStyle(node.getStyle());
       if(node instanceof Label){
           holder.setType(Holder.LABEL);
           if(holder.isSpecial())
               holder.setValue("Unknown");
           else
               holder.setValue(((Label)node).getText());
           holder.setHeight(((Label)node).getHeight());
           holder.setWidth(((Label)node).getWidth());
       }

       else if(node instanceof ImageView){
           holder.setType(Holder.IMAGE);
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
