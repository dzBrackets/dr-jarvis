package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.usedDrug;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.requestP;

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
        initDashboardController();
        dashbord_pane.toFront();
        effect= new javafx.scene.effect.GaussianBlur();
        effect.setRadius(0);
        main_panel.setEffect(effect);
        initTemplate();
        initDashboardController();
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
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/PAGES/template.fxml"));
        try {
           loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        templateController = loader.getController();
        templateStatic= templateController.container;


    }

public static WritableImage getTemplateSnap(){
     SnapshotParameters vp = new SnapshotParameters();
    templateStatic.setVisible(true);
    WritableImage wi = templateStatic.snapshot(vp, null);
    templateStatic.setVisible(false);
    return wi;
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
        requestP.offer(DashboardC.req.get(3));
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
}
