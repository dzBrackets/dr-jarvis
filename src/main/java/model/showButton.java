package model;

import com.jfoenix.controls.JFXButton;

public class showButton extends JFXButton {
    public showButton(String text){
        this.setText(text);
        this.getStyleClass().add("show_btn");
    }
}
