package dr;

import DataClass.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import static dr.FinalsVal.*;
import java.io.IOException;

public class Main extends Application {
    public static double xOffset,yOffset;
    public static dataThread<Drug> drugThread=null;
    public static dataThread<Patient> patientThread=null;
    public static dataThread<prescriptionsHistory> hisPerThread=null;
    public static dataThread<userData> userDataThread=null;
    public static dataThread<customizable> costumeThread=null;
    public static   FXMLLoader loader;

    public static Stage staticstage=null;
    public static void main(String[] args)
    {

        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("file.encoding", "UTF-8");

        try {
            drugThread=new dataThread<>("drug",Drug.class,requestD);
            drugThread.start();

            hisPerThread=new dataThread<>("prescriptions",prescriptionsHistory.class,requestH);
            hisPerThread.start();

            patientThread=new dataThread<>("patient",Patient.class,requestP);
            patientThread.start();

            userDataThread=new dataThread<>("data",userData.class,requestU);
            userDataThread.start();

            costumeThread=new dataThread<>("tempCostume",customizable.class,requestT);
            costumeThread.start();


        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        loader=new FXMLLoader(getClass().getResource("/dr/FXML/PAGES/main_pane.fxml"));
        Parent root= loader.load();
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
     //  scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");//
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        staticstage=primaryStage;


        /*  Font.loadFont(getClass().getResource("/dr/fonts/Sarabun-Regular.ttf").toExternalForm(),18);*/
        scene.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY()-yOffset);
        });
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

    }
}
