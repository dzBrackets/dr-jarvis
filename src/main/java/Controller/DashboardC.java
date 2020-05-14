package Controller;

import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import libs.requestFormer;
import model.components.recentComp;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static dr.FinalsVal.database;
import static dr.FinalsVal.requestP;

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
    public VBox container;
    //public LineChart<Number,Number> line_chart;
    boolean enough=false;
    static public final requestFormer<Patient> req=new requestFormer<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chartInit();
        update();
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
                    date_label1.setText(time);
                    date_label.setText(dmy);
                });
            }
        });
        timer.start();


    }
    void setGridList(List<Patient> list){
        recent_grid.getChildren().clear();
        for(int i=0;i<list.size();i++)
            recent_grid.add(new recentComp(list.get(i)),0,i);
    }
void loadRecent(){
    req.onReceive(v->{
        Platform.runLater(()->{
            setGridList(req.respond);

        });
    });

    requestP.offer(req.get(3));
}

void update(){
    all_prec_cpt.setText(database.getSize("prescriptions")+"");
    drug_cpt.setText(database.getSize("drug")+"");
    patient_cpt.setText(database.getSize("patient")+"");
    today_precp_cpt.setText(0+"");
    loadRecent();

}
void chartInit(){
    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("day");

    //creating the chart
    final LineChart<Number,Number> lineChart =
            new LineChart<>(xAxis,yAxis);

    lineChart.setTitle("Last month activity");
    //defining a series
    XYChart.Series<Number,Number> series = new XYChart.Series<>();
    series.setName("Patient");
    series.getData().add(new XYChart.Data<>(1, 30));
    series.getData().add(new XYChart.Data<>(2, 14));
    series.getData().add(new XYChart.Data<>(3, 15));
    series.getData().add(new XYChart.Data<>(4, 10));
    series.getData().add(new XYChart.Data<>(5, 15));
    series.getData().add(new XYChart.Data<>(6, 14));
    series.getData().add(new XYChart.Data<>(7, 22));
    series.getData().add(new XYChart.Data<>(8, 20));
    series.getData().add(new XYChart.Data<>(9, 10));
    series.getData().add(new XYChart.Data<>(10, 17));
    series.getData().add(new XYChart.Data<>(11, 12));
    series.getData().add(new XYChart.Data<>(12, 25));

    //
    XYChart.Series<Number,Number> series2 = new XYChart.Series<>();
    series2.setName("new Patient");
    series2.getData().add(new XYChart.Data<>(1, 1));
    series2.getData().add(new XYChart.Data<>(2, 2));
    series2.getData().add(new XYChart.Data<>(3, 0));
    series2.getData().add(new XYChart.Data<>(4, 5));
    series2.getData().add(new XYChart.Data<>(5, 15));
    series2.getData().add(new XYChart.Data<>(6, 10));
    series2.getData().add(new XYChart.Data<>(7, 20));
    series2.getData().add(new XYChart.Data<>(8, 14));
    series2.getData().add(new XYChart.Data<>(9, 5));
    series2.getData().add(new XYChart.Data<>(10, 0));
    series2.getData().add(new XYChart.Data<>(11, 0));
    series2.getData().add(new XYChart.Data<>(12, 1));
    lineChart.getData().add(series);
    lineChart.getData().add(series2);
    container.getChildren().add(lineChart);
}




    public void show_prescriptions(ActionEvent actionEvent) {
            MainPanelC c = Main.loader.getController();
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
