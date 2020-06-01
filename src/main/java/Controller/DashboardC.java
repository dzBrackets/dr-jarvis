package Controller;

import DataClass.Patient;
import DataClass.customizable;
import DataClass.prescriptionsHistory;
import DataClass.userData;
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
import javafx.scene.layout.*;
import libs.coronaDb.dataTostatistics;
import libs.requestFormer;
import model.components.recentComp;
import model.stageLoader;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    public VBox container;
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
void initHandler(){

    req.onReceive(v->{
        Platform.runLater(()->{
            setGridList(req.respond);});

    });
    //get prescriptions request handler
    req2.onReceive(v->{
            presList=req2.respond;
        String[] strs = req2.respond.stream()
                .map(prescriptionsHistory::getUserId).toArray(String[]::new);
        requestP.offer(req.mojoJojo("WHERE patientId = ",strs));
    });

    formerU.onReceive(v->{

        System.out.println("yeeah!!");
        if(!formerU.respond.isEmpty()){
            local_data=formerU.respond.get(0);
settingC.alpha.dispatchEvent();
chartInit();
        }
    else{ requestU.offer(formerU.post(new userData()));
        System.out.println("create new info cuz daah!");
    }
    });
    requestU.offer(formerU.get());


   formerT.onReceive(v->{
       if(!formerT.respond.isEmpty()){
           customAttrs=formerT.respond.get(0);

       }
       else{
           customizable cust = new customizable().customizable("0");
                   cust.setAttribute(0,"doctor who");
                   cust.setAttribute(1,"who know the truth");
                   cust.setAttribute(2,"dont be rude");
                   cust.setAttribute(3,"or give fuck yoo");
           requestT.offer(formerT.post(cust));
           System.out.println("create new info cuz daah!");
       }
   });
    requestT.offer(formerT.get());



}

    void setGridList(List<Patient> list){
        recent_grid.getChildren().clear();
        for(int i=0;i<list.size();i++) {
            recentComp comp = new recentComp(list.get(i));
            recent_grid.add(comp, 0, list.size()-i-1);

            int finalI = i;
            comp.showMoreListener(v->{
                stageLoader op=new stageLoader("/dr/FXML/POPUP/prescriptionDetails.fxml");
                prescriptionDetailsC controller = (prescriptionDetailsC) op.getController();
                op.show();
                controller.loadFrom(presList.get(finalI));
                controller.dad(op.getStage());
            });
            /*

             */
        }
    }
void loadRecent(){
    requestH.offer(req2.get(3));
}


    void update(){
        Thread climp = new Thread(() -> {
            String p = database.getSize("prescriptions") + "";
            String d = database.getSize("drug") + "";
            String a = database.getSize("patient") + "";
            Platform.runLater(() -> {
                all_prec_cpt.setText(p);
                drug_cpt.setText(d);
                patient_cpt.setText(a);
                today_precp_cpt.setText(local_data.getTodayStat() + "");

            });

        });
        climp.start();
        patientList.clear();
        i=0;
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
    //
    XYChart.Series<Number,Number> series2 = new XYChart.Series<>();
    series2.setName("new Patient");
    //load stat
    //dataTostatistics st=new dataTostatistics(new int[]{5,1,0,50,10,30,5,20,30,1,4,5,9,15,13,40,30,35,11,12,1,4,5,9,15,13,40,30,35});
    int[] daysByMonth = local_data.getMonthStats();
    for (int i = 0; i < daysByMonth.length; i++) {
        series2.getData().add(new XYChart.Data<>(i+1, daysByMonth[i]));
    }
    lineChart.getData().clear();
    lineChart.getData().add(series2);
   Platform.runLater(()-> container.getChildren().setAll(lineChart));
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
