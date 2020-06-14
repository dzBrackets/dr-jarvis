package Controller;

import DataClass.customizable;
import DataClass.userData;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import libs.requestFormer;
import model.components.amazingDialog;
import model.components.spawnButton;
import model.stageLoader;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    public ImageView app_icon;
    public Pane side_pane;
    public JFXButton presHistory_btn;
    public Pane presHistory_p;
    static public Pane templateStatic;
    public JFXButton close_btn;
    public JFXButton minimize_btn;
    public static templateC templateController;
    public static Stage welcomeStage;
    public DashboardC dashController;
    int i=0;
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
                local_data.checkAndReset(LocalDate.now().getDayOfMonth()-1);

                DashboardC.chartInit();
                requestT.offer(formerT.get());
                if(local_data.isfirstLaunch())
                  Main.wait.onReceive(x->  Platform.runLater(this::firstTime));
            }
            else{
                userData uss = new userData();
                requestU.offer(formerU.post(uss));
            }
        });
    }

    private void firstTime() {
        stageLoader sl=new stageLoader("Welcome :)","/dr/FXML/PAGES/welcomePage.fxml");
         welcomeStage = sl.getStage();
         Main.staticstage.hide();
        sl.setOneClose(v-> {
            System.out.println("closed");
            requestU.offer(formerU.update());
Main.staticstage.show();
            settingFirstLook();
        });

        sl.show();

        ((welcomePageC)sl.getController()).done.onReceive(v->sl.close());
    }


    private void settingFirstLook() {
        i=0;
        amazingDialog alr=new amazingDialog();
        alr.setTitle("Welcome Doctor ^^");
        alr.setContent("this is a quick guide on how to use this software.");
        JFXButton next = spawnButton.green("Next");
        alr.getButtonList().setAll(next);
        alr.setPosition(300,300);
        alr.blackBack(true);
        alr.show(Main.staticstage,false);

        next.setOnAction(v->{
            if(i==0) {
                alr.setPosition(300,50);
                alr.setBubbleDir("tr");
                alr.setTitle("here you can see the recent prescriptions");
                alr.setContent("the prescriptions are saved each time you make new one.");
            }
            if(i==1){
                alr.setPosition(100,50);
                alr.setBubbleDir("bl");
                alr.setTitle("this is the dashboard");
                alr.setContent("we put some statistics for you.");
            }
            if(i==2){
                alr.setPosition(100,300);
                alr.setBubbleDir("tl");
                        alr.setTitle("this is the prescription list");
                        alr.setContent("all prescriptions saved here.");
                presHistory_btn.fire();
                    }
            if(i==3){
                alr.setPosition(100,400);
                alr.setBubbleDir("tl");
                        alr.setTitle("this is the patient list");
                        alr.setContent("here you can manage your patient.");
                pat_btn.fire();
            }
            if(i==4){
                alr.setPosition(100,500);
                alr.setBubbleDir("tl");
                        alr.setTitle("this is the drug list list");
                        alr.setContent("here you add or remove a drug.");
                drug_btn.fire();
                    }
            if(i==5){
                alr.setPosition(100,600);
                alr.setBubbleDir("tl");
                        alr.setTitle("this is the settings");
                        alr.setContent("in first look you will see your personal info and you can edit them");
                setting_btn.fire();
                    }

                    if(i==6){
                        alr.setPosition(300,200);
                        alr.setBubbleDir("bl");
                        alr.setTitle("here you can customize your template");
                        alr.setContent("");
                        settingC.Stabpane.getSelectionModel().selectNext();

                    }
                    if(i==7){
                        alr.setPosition(100,550);
                        alr.setBubbleDir("bl");
                        alr.setTitle("here is how to add a prescription");
                        alr.setContent("you can add a prescription or a patient using this button");
                    }
                    if(i==8){
                        alr.setPosition(450,200);
                        alr.setBubbleDir("tr");
                        alr.setTitle("");
                        alr.setContent("or by clicking on special patient directly in the patient list");
                        pat_btn.fire();

                    }
                    if(i==9){
                        alr.setPosition(300,300);
                        alr.setBubbleDir("none");
                        alr.setTitle("This is get very annoying i know ':)");
                        alr.setContent("maybe you will discover more useful features by your self");
                        next.setText("finish");
                    }

                    if(i>9) {
                    dashbord_btn.fire();
                        alr.close();
                    }
            i++;
        }
        );


    }

    private customizable loadDefaultTemp() {
        customAttrs=new customizable();
        customAttrs.addAttribute("tbl1","doctor Jarvis");
        customAttrs.addAttribute("tbl2","doctor at nasa");
        customAttrs.addAttribute("tbl3","alien force");
        customAttrs.addAttribute("tbl4","new york");
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
      stageLoader sl=new stageLoader("Search for a patient","/dr/FXML/POPUP/patient_search.fxml");
        search_scene =sl.getScene();
         search_stage=sl.getStage();

        sl.show();
        effect.setRadius(3.25);
        search_stage.setOnCloseRequest(event -> {
            search_stage.close();
            effect.setRadius(0);
        });
    }

    public void show_presHistory(ActionEvent actionEvent) throws IOException {
        Main.staticstage.setTitle("Dr.jarvis - Prescriptions list");
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
        Main.staticstage.setTitle("Dr.jarvis - Dashboard");

        // settingFirstLook();
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
        Main.staticstage.setTitle("Dr.jarvis - Patients list");

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
        Main.staticstage.setTitle("Dr.jarvis - Drugs list");

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
        Main.staticstage.setTitle("Dr.jarvis - Settings");

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
