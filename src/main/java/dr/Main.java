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
import model.stageLoader;

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
    stageLoader sl;
    public static Stage staticstage=null;
    public void init() throws InterruptedException {


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

         Platform.runLater(()->sl=new stageLoader("Dr.jarvis - Dashboard","/dr/FXML/PAGES/main_pane.fxml"));
        System.out.println("wait");








    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage=sl.getStage();
        staticstage=primaryStage;

        staticstage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        sl.show();


    }

}
