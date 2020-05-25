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
   public Parent root=null;
   public Object controller;
   public Stage stage;
   private Scene scene;

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
    }
    public void show(){
         stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public void close(){
        stage.close();
    }
}
