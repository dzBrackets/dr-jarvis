package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelC  implements Initializable {
    public  AnchorPane main_panel;
    public AnchorPane content_panel;
    public static  Scene search_scene;
    public static Stage search_stage;
    public static Stage drugList;
    public static GaussianBlur effect;
    public Pane drug_panel;
    public Pane patient_panel;
    public Pane dashbord_pane;
    public Pane setting_pane;

    public JFXButton dashbord_btn;
    public JFXButton prescription_btn;
    public JFXButton pat_btn;
    public JFXButton drug_btn;
    public JFXButton setting_btn;
    public JFXButton quick_btn;

   static double xOffset,yOffset;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    patient_panel.toFront();


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

        effect= new javafx.scene.effect.GaussianBlur();
        effect.setRadius(3.25);
        main_panel.setEffect(effect);
        search_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                search_stage.close();
                effect.setRadius(0);
            }
        });

        search_scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset=event.getSceneX();
                yOffset=event.getSceneY();
            }
        });
        search_scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                search_stage.setX(event.getScreenX() - xOffset);
                search_stage.setY(event.getScreenY()-yOffset);
            }
        });


    }

    public void new_precP(ActionEvent actionEvent) throws IOException {
        reset_btn_Opicity();
        prescription_btn.getGraphic().setOpacity(1);
    }

    public void show_DashP(ActionEvent actionEvent) {
        dashbord_pane.toFront();
        dashbord_pane.setVisible(true);
        reset_btn_Opicity();
        dashbord_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        patient_panel.setVisible(false);
        setting_pane.setVisible(false);
    }

    public void show_patientP(ActionEvent actionEvent) {
        patient_panel.toFront();
        patient_panel.setVisible(true);
        reset_btn_Opicity();
        pat_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        setting_pane.setVisible(false);
        dashbord_pane.setVisible(false);
    }

    public void show_drugPanel(ActionEvent actionEvent) {
        drug_panel.toFront();
        drug_panel.setVisible(true);
        reset_btn_Opicity();
        drug_btn.getGraphic().setOpacity(1);
        patient_panel.setVisible(false);
        setting_pane.setVisible(false);
        dashbord_pane.setVisible(false);
    }

    public void show_SettingP(ActionEvent actionEvent) {
        setting_pane.toFront();
        setting_pane.setVisible(true);
        reset_btn_Opicity();
        setting_btn.getGraphic().setOpacity(1);
        drug_panel.setVisible(false);
        patient_panel.setVisible(false);
        dashbord_pane.setVisible(false);
    }
    public void reset_btn_Opicity(){
        prescription_btn.getGraphic().setOpacity(0.5);
        pat_btn.getGraphic().setOpacity(0.5);
        drug_btn.getGraphic().setOpacity(0.5);
        setting_btn.getGraphic().setOpacity(0.5);
        dashbord_btn.getGraphic().setOpacity(0.5);
    }
}
