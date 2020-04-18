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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelC  implements Initializable {
    public  AnchorPane main_panel;
    public AnchorPane content_panel;
    public static Scene sc ;
    public  static  Stage s;
    public static GaussianBlur effect;
    public  Pane quick_panel;
    public Pane drug_panel;
    public Pane patient_panel;
    public JFXButton dashbord_btn;
    public JFXButton prescription_btn;
    public JFXButton pat_btn;
    public JFXButton drug_btn;
    public JFXButton setting_btn;
    public JFXButton quick_btn;

    double xOffset,yOffset;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    patient_panel.toFront();

    }

    public void add_quick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/patient_search.fxml"));
        Scene sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Stage s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
         s.initStyle(StageStyle.TRANSPARENT);
        s.show();
    }

    public void new_precP(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/quick_panel.fxml"));
         sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        s.initStyle(StageStyle.TRANSPARENT);
        s.show();
         effect= new javafx.scene.effect.GaussianBlur();
        effect.setRadius(3.25);
        main_panel.setEffect(effect);

        sc.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset=event.getSceneX();
                yOffset=event.getSceneY();
            }
        });
        sc.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s.setX(event.getScreenX() - xOffset);
                s.setY(event.getScreenY()-yOffset);
            }
        });

        reset_btn_Opicity();
        prescription_btn.getGraphic().setOpacity(1);

    }

    public void show_DashP(ActionEvent actionEvent) {
        reset_btn_Opicity();
        dashbord_btn.getGraphic().setOpacity(1);
    }

    public void show_patientP(ActionEvent actionEvent) {
        patient_panel.toFront();
        reset_btn_Opicity();
        pat_btn.getGraphic().setOpacity(1);
    }

    public void show_drugPanel(ActionEvent actionEvent) {
        drug_panel.toFront();
        reset_btn_Opicity();
        drug_btn.getGraphic().setOpacity(1);
    }

    public void show_SettingP(ActionEvent actionEvent) {
        reset_btn_Opicity();
        setting_btn.getGraphic().setOpacity(1);
    }
    public void reset_btn_Opicity(){
        prescription_btn.getGraphic().setOpacity(0.5);
        pat_btn.getGraphic().setOpacity(0.5);
        drug_btn.getGraphic().setOpacity(0.5);
        setting_btn.getGraphic().setOpacity(0.5);
        dashbord_btn.getGraphic().setOpacity(0.5);
    }
}
