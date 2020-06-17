package dr;

import Controller.alertBox;
import DataClass.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import libs.stackTraceViewer;
import model.components.amazingDialog;
import model.components.spawnButton;
import model.stageLoader;

import static dr.FinalsVal.*;

public class Main extends Application {
    public static dataWorker<Drug> drugThread=null;
    public static dataWorker<Patient> patientThread=null;
    public static dataWorker<prescriptionsHistory> hisPerThread=null;
    public static dataWorker<userData> userDataWorker =null;
    public static dataWorker<customizable> costumeThread=null;
   static public stageLoader sl=null;
    public static Stage staticstage=null;
    public static async wait=new async();
    private boolean Error=false;
    public void init() {


        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("file.encoding", "UTF-8");

        try {
            drugThread=new dataWorker<>("drug",Drug.class,requestD);
            drugThread.start();
            hisPerThread=new dataWorker<>("prescriptions",prescriptionsHistory.class,requestH);
            hisPerThread.start();

            patientThread=new dataWorker<>("patient",Patient.class,requestP);
            patientThread.start();

            userDataWorker =new dataWorker<>("data",userData.class,requestU);
            userDataWorker.start();

            costumeThread=new dataWorker<>("tempCostume",customizable.class,requestT);
            costumeThread.start();

            Platform.runLater(()->sl=new stageLoader("Dr.jarvis - Dashboard","/dr/FXML/PAGES/main_pane.fxml"));
            System.out.println("wait");

        } catch (Exception e) {
           Error=true;
            Platform.runLater(()->{
                sl =new stageLoader("DrJarvis -ERROR "+e.getMessage(),"/dr/FXML/POPUP/alertBox.fxml");
                amazingDialog alr=new amazingDialog((alertBox) sl.getController());
                alr.setTitle("Ohh ohh!!!");
                alr.setContent("Something wrong happen. try to run the app as administrator");
                alr.setImage(amazingDialog.WARNING);
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
