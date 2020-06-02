package model.components;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class template_Pane extends Pane {
    private JFXButton edit ,use,add;
    private Label selected;
    public template_Pane (){
edit =new JFXButton("Edit...");
use=new JFXButton("Use...");
selected=new Label("Selected");
add =new JFXButton();
this.getStyleClass().add("template_pane");
show_use();

    }
    public void show_edit(){
       /* this.setPrefWidth(100);
        this.setHeight(30);*/

        /*width and height*/
        edit.setPrefWidth(78);
        edit.setPrefHeight(27);
        selected.setPrefWidth(99);
        selected.setPrefHeight(25);
        /*layoutx and y*/
        edit.setLayoutX(34);
        edit.setLayoutY(61);
        selected.setLayoutX(23);
        selected.setLayoutY(122);
        selected.setAlignment(Pos.CENTER);
        edit.getStyleClass().add("edit_use_btns");
        selected.getStyleClass().add("selected_btn");
        edit.setFocusTraversable(false);
        selected.setFocusTraversable(false);
        this.getChildren().clear();
        this.getChildren().addAll(edit,selected);

    }
    public void show_use(){

        use.setPrefWidth(78);
        use.setPrefHeight(27);
        use.setLayoutX(34);
        use.setLayoutY(85);
        use.getStyleClass().add("edit_use_btns");
        use.setFocusTraversable(false);


        this.getChildren().clear();
        this.getChildren().addAll(use);

    }
    public void show_add(){
        add.setPrefWidth(USE_PREF_SIZE);
        add.setPrefHeight(USE_PREF_SIZE);
        ImageView img =new ImageView("dr/image/addtemplateicon.png");
        img.setFitWidth(80);
        img.setFitHeight(77);
        add.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        add.setGraphic(img);
        add.setAlignment(Pos.CENTER);
        add.setLayoutX(24);
        add.setLayoutY(47);
        add.setFocusTraversable(false);
        this.getChildren().clear();
        this.getChildren().addAll(add);

    }
    public void  use_btn_Listener(EventHandler<ActionEvent> e){
        use.setOnAction(e);
    }

}
