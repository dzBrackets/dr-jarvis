package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import dr.async;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import libs.requestFormer;
import model.components.recentComp;
import model.stageLoader;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

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
    public Pane container;
   static public Pane stCntainer;

    public List<prescriptionsHistory> presList;
    public ObservableList<Patient> patientList= FXCollections.observableArrayList();
    //public LineChart<Number,Number> line_chart;
    boolean enough=false;
    int i=0;

    static public final requestFormer<prescriptionsHistory> req2=new requestFormer<>();
    static public final requestFormer<Patient> req =new requestFormer<>();
    static async initStat=new async();
    @Override

    public void initialize(URL location, ResourceBundle resources) {
        stCntainer=container;
        chartInit();
        initHandler();
        update();
        loadRecent();



        pane1.hoverProperty().addListener((observable, oldValue, newValue) -> {
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
public void initHandler(){
    req.onReceive(v->{
        Platform.runLater(()-> setGridList(req.respond));

    });
    //get prescriptions request handler
    req2.onReceive(v->{
        System.out.println("init recent");
        presList=req2.respond;
        String[] strs = req2.respond.stream()
                .map(prescriptionsHistory::getUserId).toArray(String[]::new);
        requestP.offer(req.mojoJojo("getPatientId",strs));
    });


    requestU.offer(formerU.get());






}

    void setGridList(List<Patient> list){
        recent_grid.getChildren().clear();
        String[] diagnoList = presList.stream().map(prescriptionsHistory::getDiagnosis).toArray(String[]::new);
        for(int i=0;i<list.size();i++) {
            recentComp comp = new recentComp(list.get(i),diagnoList[i]);
            recent_grid.add(comp, 0, list.size()-i-1);

            int finalI = i;
            comp.showMoreListener(v->{
                stageLoader op=new stageLoader("Prescription details","/dr/FXML/POPUP/prescriptionDetails.fxml");
                prescriptionDetailsC controller = (prescriptionDetailsC) op.getController();
                op.show();
                controller.loadFrom(presList.get(finalI));
                controller.dad(op.getStage());
            });
            /*

             */
        }
    }
    public void loadRecent(){
    requestH.offer(req2.get(3));
}


    void update(){
        Thread climp = new Thread(() -> {
           // String p = database.getSize("prescriptions") + "";
            String d = database.getSize("drug") + "";
            String a = database.getSize("patient") + "";
            Platform.runLater(() -> {
                all_prec_cpt.setText(local_data.getTodayStat()+"");
                drug_cpt.setText(d);
                patient_cpt.setText(a);
                today_precp_cpt.setText(local_data.getMonthlyStat() + "");
                doctor_name_label.setText("Dr."+local_data.getName().split(" ")[0]);

            });

        });
        climp.start();
        patientList.clear();
        i=0;
    loadRecent();
}
static void chartInit(){
    //defining the axes
    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel(local_data.getTodayStat() +" today");

    //creating the chart
    final LineChart<Number,Number> lineChart =
            new LineChart<>(xAxis,yAxis);

    lineChart.setTitle(" Monthly activity");
    //defining a series
    //
    XYChart.Series<Number,Number> series2 = new XYChart.Series<>();
    series2.setName("Prescriptions/Day");
    //load stat
    int[] daysByMonth = local_data.getMonthStats();
    for (int i = 0; i < daysByMonth.length; i++) {
        if(daysByMonth[i]!=0)
        series2.getData().add(new XYChart.Data<>(i+1, daysByMonth[i]));
    }
    lineChart.getData().clear();
    lineChart.getData().add(series2);
    lineChart.setPrefSize(589,277);
   Platform.runLater(()-> stCntainer.getChildren().setAll(lineChart));
}




    public void show_prescriptions(ActionEvent actionEvent) {
            MainPanelC c = (MainPanelC) Main.sl.getController();
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
