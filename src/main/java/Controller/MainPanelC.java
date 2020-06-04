package Controller;

import DataClass.customizable;
import DataClass.userData;
import com.jfoenix.controls.JFXButton;
import dr.async;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.requestFormer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

public class MainPanelC  implements Initializable {
    public  AnchorPane main_panel;
    public AnchorPane content_panel;
    public static  Scene search_scene;
    public static Stage search_stage;
    public static GaussianBlur effect;
    public Pane drug_panel;
    public Pane patient_panel;
    public Pane dashbord_pane;
    public  Pane setting_pane;
    public JFXButton dashbord_btn;
    public JFXButton pat_btn;
    public JFXButton drug_btn;
    public JFXButton setting_btn;
    public JFXButton quick_btn;

   static double xOffset,yOffset;
    public ImageView app_icon;
    public Pane side_pane;
    public JFXButton presHistory_btn;
    public Pane presHistory_p;
    static public Pane templateStatic;
    public JFXButton close_btn;
    public JFXButton minimize_btn;
    static templateC templateController;
    public DashboardC dashController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initWaiters();
        initSensitiveData();
        initDashboardController();
        dashbord_pane.toFront();
        effect= new javafx.scene.effect.GaussianBlur();
        effect.setRadius(0);
        main_panel.setEffect(effect);
        initDashboardController();
        hover_btn();

    }

    private void initWaiters() {
        formerT.onReceive(v->{
            if(formerT.respond.isEmpty()){
                loadDefaultTemp();
            }
            else {
                try{
                customAttrs = formerT.respond.get(local_data.getSelectedTemplate());}
                catch (IndexOutOfBoundsException ex){
                    customAttrs = formerT.respond.get(0);
                    local_data.setSelectedTemplate(0);
                    requestU.offer(new requestFormer<userData>().update());
                }
            }
            initTemplate();
            settingC.alpha.dispatchEvent();
            settingC.beta.dispatchEvent();
        });

        formerU.onReceive(v->{

            System.out.println("yeeah!!");
            if(!formerU.respond.isEmpty()){
                local_data=formerU.respond.get(0);
                DashboardC.chartInit();
                requestT.offer(formerT.get());

            }
            else{ requestU.offer(formerU.post(new userData()));
                System.out.println("create new info cuz daah!");
            }
        });
    }

    private customizable loadDefaultTemp() {
        customAttrs=new customizable();
        customAttrs.addAttribute("tbl1","doctor daudji aymen");
        customAttrs.addAttribute("tbl2","specialist a programing");
        customAttrs.addAttribute("tbl3","ouled aich");
        customAttrs.addAttribute("tbl4","blida");
        customAttrs.addAttribute("c1","0x6a1b9aff");
        customAttrs.addAttribute("c2","0x0d47a1ff");

        Platform.runLater(()->{requestT.offer(formerT.post(customAttrs));});
        return customAttrs;

    }

    private void initSensitiveData() {

    }

    public void initDashboardController(){

    FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/PAGES/Dashboard.fxml"));

    try {
        loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }

    dashController = loader.getController();
    dashbord_pane.getChildren().add( dashController.dashborad_pane);
    dashbord_pane.setVisible(true);

}

    public void initTemplate(){

        FXMLLoader loader =new FXMLLoader();
        try {
            System.out.println("lets go");
            loader.setLocation(customAttrs.URL());

        } catch (IOException e) {
            requestT.offer(new requestFormer<customizable>().remove(customAttrs));
            if(formerT.respond.size()>0&&formerT.respond.get(0)!=customAttrs){
                customAttrs = formerT.respond.get(0);
                local_data.setSelectedTemplate(0);
                requestU.offer(new requestFormer<userData>().update());
            }
        }
        try {
            loader.setLocation(customAttrs.URL());
        } catch (IOException e) {
            loader.setLocation(getClass().getResource("/dr/FXML/PAGES/template.fxml"));
        }


        try {
           loader.load();
        } catch (IOException e) {
e.getStackTrace();        }
        templateController = loader.getController();
        templateStatic= templateController.container;
        templateController.setDoctorLocalInfo();

    }


    public void  add_quick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/patient_search.fxml"));
        search_scene =new Scene(root);
        search_scene.setFill(Color.TRANSPARENT);
        search_scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
         search_stage=new Stage();
        search_stage.initModality(Modality.APPLICATION_MODAL);
        search_stage.setScene(search_scene);
         search_stage.initStyle(StageStyle.TRANSPARENT);
        search_stage.show();
        effect.setRadius(3.25);
        search_stage.setOnCloseRequest(event -> {
            search_stage.close();
            effect.setRadius(0);
        });
        search_scene.setOnMousePressed(event -> {
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });
        search_scene.setOnMouseDragged(event -> {
            search_stage.setX(event.getScreenX() - xOffset);
            search_stage.setY(event.getScreenY()-yOffset);
        });


    }

    public void show_presHistory(ActionEvent actionEvent) throws IOException {

        reset_btn_Opicity();
        presHistory_btn.getGraphic().setOpacity(1);
        presHistory_p.toFront();
        presHistory_p.setVisible(true);
        dashbord_pane.setVisible(false);
        drug_panel.setVisible(false);
        patient_panel.setVisible(false);
        setting_pane.setVisible(false);
    }

    public void show_DashP(ActionEvent actionEvent) {
        dashController.update();
        dashbord_pane.toFront();
        dashbord_pane.setVisible(true);
        reset_btn_Opicity();
        dashbord_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        patient_panel.setVisible(false);
        setting_pane.setVisible(false);
        presHistory_p.setVisible(false);
    }

    public void show_patientP(ActionEvent actionEvent) {
        patient_panel.toFront();
        patient_panel.setVisible(true);
        reset_btn_Opicity();
        pat_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        setting_pane.setVisible(false);
        dashbord_pane.setVisible(false);
        presHistory_p.setVisible(false);

    }

    public void show_drugPanel(ActionEvent actionEvent) {
        drug_panel.toFront();
        drug_panel.setVisible(true);
        reset_btn_Opicity();
        drug_btn.getGraphic().setOpacity(1);
        patient_panel.setVisible(false);
        setting_pane.setVisible(false);
        dashbord_pane.setVisible(false);
        presHistory_p.setVisible(false);
    }

    public void show_SettingP(ActionEvent actionEvent) {
        setting_pane.toFront();
        setting_pane.setVisible(true);
        reset_btn_Opicity();
        setting_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        patient_panel.setVisible(false);
        dashbord_pane.setVisible(false);
        presHistory_p.setVisible(false);

    }
    public void reset_btn_Opicity(){
        presHistory_btn.getGraphic().setOpacity(0.5);
        pat_btn.getGraphic().setOpacity(0.5);
        drug_btn.getGraphic().setOpacity(0.5);
        setting_btn.getGraphic().setOpacity(0.5);
        dashbord_btn.getGraphic().setOpacity(0.5);
    }



    public void minimize_app(ActionEvent actionEvent) {
      Stage s= (Stage) main_panel.getScene().getWindow();
      s.setIconified(true);
    }

    public void close_app(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
    public void hover_btn (){
        close_btn.hoverProperty().addListener((observable, oldValue, newValue) -> {
            ImageView white_close =new ImageView("dr/image/delete_24pxwhite.png");
            white_close.setFitHeight(20);
            white_close.setFitWidth(20);
            ImageView black_close =new ImageView("dr/image/close_window_100px.png");
            black_close.setFitHeight(20);
            black_close.setFitWidth(20);

            if(close_btn.isHover()){
                close_btn.setGraphic(white_close);
            }
            else {
                close_btn.setGraphic(black_close);
            }


        });
        minimize_btn.hoverProperty().addListener((observable, oldValue, newValue) -> {
            ImageView white_mini =new ImageView("dr/image/minus_24pxwhite.png");
            white_mini.setFitHeight(20);
            white_mini.setFitWidth(20);
            ImageView black_mini =new ImageView("dr/image/minimize.png");
            black_mini.setFitHeight(20);
            black_mini.setFitWidth(20);
            if(minimize_btn.isHover()){
                minimize_btn.setGraphic(white_mini);
            }
            else {
                minimize_btn.setGraphic(black_mini);
            }

        });
    }
}
