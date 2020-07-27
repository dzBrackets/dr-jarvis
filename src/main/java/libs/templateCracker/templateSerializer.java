package libs.templateCracker;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class templateSerializer {
    ArrayList<Holder> holders;
    public templateSerializer(Pane node){
        for (Node element:node.getChildren()) {
            holders.add(nodeToHolder(element));
        }
    }
    Holder nodeToHolder(Node node){
        Holder holder=new Holder();
            holder.setType(Holder.LABEL);
            holder.setYPos(node.getLayoutY());
            holder.setYPos(node.getLayoutX());
            holder.setName(node.getId());
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
