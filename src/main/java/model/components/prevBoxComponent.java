package model.components;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class prevBoxComponent extends Pane {
    public final JFXButton edit =new JFXButton("Edit...");
    public final JFXButton use=new JFXButton("Use...");
    public final JFXButton add=new JFXButton();
    public final Label selected=new Label("Selected");

    public prevBoxComponent(){
setContentPosition();
    }

    private void setContentPosition() {
        this.getStyleClass().add("template_pane");

        edit.setPrefWidth(78);
        edit.setPrefHeight(27);
        edit.setLayoutX(34);
        edit.setLayoutY(61);
        selected.setPrefWidth(99);
        selected.setPrefHeight(25);
        selected.setLayoutX(23);
        selected.setLayoutY(122);
        selected.setAlignment(Pos.CENTER);
        edit.getStyleClass().add("edit_use_btns");
        selected.getStyleClass().add("selected_btn");
        use.setPrefWidth(78);
        use.setPrefHeight(27);
        use.setLayoutX(34);
        use.setLayoutY(85);
        use.getStyleClass().add("edit_use_btns");
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
        edit.setFocusTraversable(false);
        selected.setFocusTraversable(false);
        use.setFocusTraversable(false);


    }



    public void selected(){

        this.getChildren().clear();
        this.getChildren().addAll(edit,selected);

    }
    public void notSelected(){
        this.getChildren().clear();
        this.getChildren().addAll(use);
    }
    public void lastOne(){
        this.getChildren().clear();
        this.getChildren().addAll(add);
    }
    public void  use_btn_Listener(EventHandler<ActionEvent> e){
        use.setOnAction(e);
    }

}
