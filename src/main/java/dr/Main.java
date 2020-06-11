package dr;

import Controller.alertBox;
import DataClass.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import libs.stackTraceViewer;
import model.components.dialog;
import model.components.spawnButton;
import model.stageLoader;

import static dr.FinalsVal.*;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static double xOffset,yOffset;
    public static dataThread<Drug> drugThread=null;
    public static dataThread<Patient> patientThread=null;
    public static dataThread<prescriptionsHistory> hisPerThread=null;
    public static dataThread<userData> userDataThread=null;
    public static dataThread<customizable> costumeThread=null;
    public static   FXMLLoader loader;
   static public stageLoader sl=null;
    public static Stage staticstage=null;
    public static async wait=new async();
    private boolean Error=false;
    public void init() {


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

            Platform.runLater(()->sl=new stageLoader("Dr.jarvis - Dashboard","/dr/FXML/PAGES/main_pane.fxml"));
            System.out.println("wait");

        } catch (Exception e) {
           Error=true;
            Platform.runLater(()->{
                sl =new stageLoader("DrJarvis -ERROR "+e.getMessage(),"/dr/FXML/POPUP/alertBox.fxml");
                dialog alr=new dialog((alertBox) sl.getController());
                alr.setTitle("Ohh ohh!!!");
                alr.setContent("Something wrong happen. try to run the app as administrator");
                alr.setImage(dialog.WARNING);
                //sl.getStage().setScene(new Scene(alr.self.container));

                JFXButton exit = spawnButton.red("Exit");
                JFXButton show = spawnButton.blue("show...");
            alr.getButtonList().setAll(show,exit);

                exit.setOnAction(v->{
                    Platform.exit();
                    System.exit(0);
                });
                show.setOnAction(v->{
                    stackTraceViewer tracer = new stackTraceViewer(e);
                    tracer.showAndWait();
                });
            });


        }









    }

    @Override
    public void start(Stage primaryStage) {

        if (!Error) {
            primaryStage = sl.getStage();
            staticstage = primaryStage;
            wait.dispatchEvent();
            staticstage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
        }
        sl.show();

    }
}
