package Controller;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.components.recentComp;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static java.time.temporal.TemporalQueries.localDate;

public class DashboardC implements Initializable {


    public AnchorPane dashborad_pane;
    public Pane recent_pane;
    public JFXButton shwo_all_btn;
    public Pane boxes_pane;
    public Label doctor_name_label;
    public Label date_label;
    public Label all_prec_cpt;
    public Label drug_cpt;
    public Label patient_cpt;
    public Label today_precp_cpt;
    public Pane recent_pane1;
    public JFXButton shwo_all_btn1;
    public Button item1;
    public Label item1_name_label;
    public JFXButton show_pres_btn;
    public Label item1_age;
    public Label item1_diago;
    public Label item1_time;
    public Button item2;
    public Label item2_name_label1;
    public Label item2_age;
    public JFXButton show_pres_btn2;
    public Label item2_diago;
    public Label item2_time;
    public Button item3;
    public Label item1_name_label11;
    public JFXButton show_pres_btn3;
    public Label item3_age;
    public Label item3_diago;
    public Label item3_time;
    public Label date_label1;
    public Pane recent_btn_pane1;
    public GridPane recent_grid;
    boolean enough=false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
                    date_label1.setText(time);
                    date_label.setText(dmy);
                });
            }
        });
        timer.start();
        recent_grid.add(new recentComp(new Patient().Patient("001", "masoud", "ouzil", LocalDate.now(), 1, LocalDate.now(), "N/D")),0,1);
    //    recent_grid.add(new recentComp(),0,1);
       // recent_grid.add(new recentComp(),0,2);
    }

    public void pan_selected(MouseEvent mouseEvent) {


    }

    public void show_prescription(ActionEvent actionEvent) {
    }

    public void show_all_pres(ActionEvent actionEvent) {
    }
}
