package dr;

import DataClass.Drug;
import DataClass.Patient;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static dr.FinalsVal.*;
import java.io.IOException;

public class Main extends Application {
    double xOffset,yOffset;

    public static void main(String[] args)
    {
        dataThread<Drug> drugThread=null;
        dataThread<Patient> patientThread=null;
        try {
            drugThread=new dataThread<>("drug",Drug.class,requestD,respondD);
            drugThread.start();
            patientThread=new dataThread<>("patient",Patient.class,requestP,respondP);
            patientThread.start();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/dr/FXML/PAGES/main_pane.fxml"));
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
       scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
      /*  Font.loadFont(getClass().getResource("/dr/fonts/Sarabun-Regular.ttf").toExternalForm(),18);*/
        scene.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY()-yOffset);
        });


    }
}
