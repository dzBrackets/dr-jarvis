package dr;

import DataClass.Drug;
import DataClass.Patient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static dr.FinalsVal.*;
import java.io.IOException;

public class Main extends Application {

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

    }
}
