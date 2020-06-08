package model.components;

import com.jfoenix.controls.JFXButton;

public class spawnButton {
    public static JFXButton green(String text){
        JFXButton btn=new JFXButton(text);
        btn.setPrefHeight(25);
        btn.setPrefWidth(85);
        btn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 100px; -fx-background-color: #0ACF83;-fx-text-fill:white;t-fx-font-family: 'Sarabun Medium ' ;");
        btn.getStyleClass().add("btn_btn");
        return btn;
    }
    public static final JFXButton blue(String text){
        JFXButton btn=new JFXButton(text);
        btn.setPrefHeight(25);
        btn.setPrefWidth(85);
        btn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 100px; -fx-background-color: #01B9CA;-fx-text-fill:white;t-fx-font-family: 'Sarabun Medium ' ;");
        btn.getStyleClass().add("btn_btn");
        return btn;

    }
    public static final JFXButton trans(String text){
        JFXButton btn=new JFXButton(text);
        btn.setPrefHeight(25);
        btn.setPrefWidth(85);
        btn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 100px; -fx-background-color: transparent;-fx-text-fill:white;t-fx-font-family: 'Sarabun Medium ' ;");
        btn.getStyleClass().add("btn_btn");
        return btn;

    }
    public static final JFXButton gray(String text){
        JFXButton btn=new JFXButton(text);
        btn.setPrefHeight(25);
        btn.setPrefWidth(85);
        btn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 100px; -fx-background-color: #949494;-fx-text-fill:white;t-fx-font-family: 'Sarabun Medium ' ;");
        btn.getStyleClass().add("btn_btn");
        return btn;


    }
    public static final JFXButton red(String text){
        JFXButton btn=new JFXButton(text);
        btn.setPrefHeight(25);
        btn.setPrefWidth(85);
        btn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 100px; -fx-background-color: #F24E1E;-fx-text-fill:white;t-fx-font-family: 'Sarabun Medium ' ;");
        btn.getStyleClass().add("btn_btn");
        return btn;

    }
}
