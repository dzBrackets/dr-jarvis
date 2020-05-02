package Controller;

import DataClass.Patient;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import model.showButton;

import java.net.URL;
import java.util.ResourceBundle;

public class prescriptionsHistoryC implements Initializable {
    public AnchorPane prescriptionsHistory;

    public TableView history_table;
    public TableColumn presId;
    public TableColumn userId;
    public TableColumn date;
    public TableColumn show_c;

    public Spinner show_spinner;
    public TextField write_TXF;
  //  public libs.cellController<Patient> cellController=new cellController<>();//

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 initCol();
    }
    public void initCol(){
        presId.getStyleClass().add("start");
        show_c.getStyleClass().add("end");
        presId.setCellValueFactory(new PropertyValueFactory<>("PrescriptionId"));
        userId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
     //   show_c.setCellFactory(cellController.BCellFactory(new showButton("show")));//

    }
}
