package model;

import dr.async;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static dr.FinalsVal.APP_ICON;

public class stageLoader {
   private Parent root=null;
   private Object controller;
   private Stage stage;
   private Scene scene;
private double xOffset,yOffset;
private  async close;
    public stageLoader(String name, Pane node) {
        root=  node;

    scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
    controller = null;
    stage = new Stage();



        scene.setOnMousePressed(event -> {

        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    });
        scene.setOnMouseDragged(event -> {

        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY()-yOffset);
    });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(APP_ICON);
        stage.setTitle(name);
    }
    public stageLoader(String name,String URL){
        FXMLLoader loader =new FXMLLoader(getClass().getResource(URL));
        try {
            root= loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
         scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        controller = loader.getController();
        stage = new Stage();



        scene.setOnMousePressed(event -> {

            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY()-yOffset);
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(APP_ICON);
        stage.setTitle(name);

    }
    public void show(){

        stage.show();
    }
    public void show(Double X,Double Y){
stage.setX(X);
stage.setY(Y);
        stage.show();
    }
    public void close(){
        stage.close();
        close.dispatchEvent();
    }
public void setOneClose(InvalidationListener v){
        close=new async();
    close.onReceive(v);
}
    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void disableDrag() {
        scene.setOnMousePressed(event -> {

        });
        scene.setOnMouseDragged(event -> {

        });
    }
}
