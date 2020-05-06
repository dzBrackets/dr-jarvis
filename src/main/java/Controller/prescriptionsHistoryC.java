package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import libs.requestFormer;
import model.showButton;

import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

public class prescriptionsHistoryC implements Initializable {
    public AnchorPane prescriptionsHistory;

    public TableView<prescriptionsHistory> history_table;
    public TableColumn<prescriptionsHistory,String> presId;
    public TableColumn <prescriptionsHistory,String> userId;
    public TableColumn <prescriptionsHistory,String> date;
    public TableColumn <prescriptionsHistory,String> show_c;

    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
   public libs.cellController<prescriptionsHistory> cellController=new cellController<>();

    private final requestFormer<prescriptionsHistory> req=formerH;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
 initCol();
 loadData();
    }
    public void initCol(){
        presId.getStyleClass().add("start");
        show_c.getStyleClass().add("end");
        presId.setCellValueFactory(new PropertyValueFactory<>("presId"));
        userId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        show_c.setCellFactory(cellController.BCellFactory(new showButton("Detail...")));
    }
    public void  loadData(){

        req.onReceive(v->{
            history_table.setItems(req.respond);
        });

        requestH.offer(req.get());

    }
}
