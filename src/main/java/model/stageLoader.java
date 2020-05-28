package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class stageLoader {
   private Parent root=null;
   private Object controller;
   private Stage stage;
   private Scene scene;
private double xOffset,yOffset;
    public stageLoader(String URL){
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
    }
    public void show(){
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public void close(){
        stage.close();
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
}
