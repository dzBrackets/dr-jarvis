package Controller;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import libs.requestFormer;
import model.components.recentComp;
import sun.security.krb5.internal.PAData;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static dr.FinalsVal.database;
import static dr.FinalsVal.requestP;
import static java.time.temporal.TemporalQueries.localDate;

public class DashboardC implements Initializable {


    public AnchorPane dashborad_pane;
    public Pane boxes_pane;
    public Label doctor_name_label;
    public Label date_label;
    public Label all_prec_cpt;
    public Label drug_cpt;
    public Label patient_cpt;
    public Label today_precp_cpt;
    public Pane recent_pane1;

    public Label date_label1;
    public Pane recent_btn_pane1;
    public GridPane recent_grid;
    public Pane pane1;
    public Pane pane2;
    public Pane pane3;
    public Pane pane4;
    public JFXButton show_all_btn;
    boolean enough=false;
    static public final requestFormer<Patient> req=new requestFormer<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane1.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {

            } else {

            }
        });


        Thread timer = new Thread(() -> {
            SimpleDateFormat clock = new SimpleDateFormat("k:mm:ss");
            SimpleDateFormat date = new SimpleDateFormat("d MMMMM y");
            while(!enough) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                Date dateNow = new Date();
                final String time = clock.format(dateNow);
                final String dmy=date.format(dateNow);
                Platform.runLater(()-> {
                    date_label1.setText("age"+time);
                    date_label.setText(dmy);
                });
            }
        });
        timer.start();
loadRecent();


    }
    void setGridList(List<Patient> list){
        recent_grid.getChildren().clear();
        for(int i=0;i<list.size();i++)
            recent_grid.add(new recentComp(list.get(i)),0,i);
    }
void loadRecent(){
    req.onReceive(v->{
        Platform.runLater(()->{
            all_prec_cpt.setText(database.getUUID("prescriptions")+"");
            setGridList(req.respond);

        });
    });

    requestP.offer(req.get(3));
}





    public void show_prescriptions(ActionEvent actionEvent) {
        MainPanelC c =Main.loader.getController();
        c.reset_btn_Opicity();
        c.presHistory_btn.getGraphic().setOpacity(1);
        c.presHistory_p.toFront();
        c.presHistory_p.setVisible(true);
        c.dashbord_pane.setVisible(false);
        c.drug_panel.setVisible(false);
        c.patient_panel.setVisible(false);
        c.setting_pane.setVisible(false);

    }
}
