package dr;

import Controller.loading;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static dr.FinalsVal.APP_ICON;

public class preLoader extends Preloader {

    static public Stage preloaderStage;
    public static loading controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
            preloaderStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dr/FXML/PAGES/LoadingPage.fxml"));

        Parent root= loader.load();
        Scene scene=new Scene(root);
         controller = loader.getController();
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(APP_ICON);
        primaryStage.setTitle("Dr.jarvis");
            primaryStage.show();


        Thread timer = new Thread(() -> {
        for(int i=0;i<1000;i++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            preLoader.controller.boost();
        }

        });
        timer.start();

        }

        @Override
        public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
            if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
                preLoader.controller.spinner.setProgress(1);

                Thread timer = new Thread(() -> {
                    try {
                        Thread.sleep(500);
                        Platform.runLater(()->preloaderStage.close());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                timer.start();
            }
        }
    }

