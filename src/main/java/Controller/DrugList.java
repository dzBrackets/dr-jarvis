package Controller;

import DataClass.Drug;
import com.sun.javafx.charts.Legend;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.cellController;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libs.coronaDb.coCollection;
import libs.requestFormer;
import model.showButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static dr.FinalsVal.*;
public class DrugList  implements Initializable {
    public TableView<Drug> drug_table;
    public TableColumn<Drug,String>  code_C;
    public TableColumn<Drug,String> name_C;
    public TableColumn<Drug,String>  type_C;
    public TableColumn<Drug, String> doss_C;
    public TableColumn<Drug, String>  notice_C;
    public TableColumn<Drug, String> menu_C;
   static ObservableList<Drug> data = FXCollections.observableArrayList();
static public  Stage s;
    cellController<Drug> cellController = new cellController<>();

    private requestFormer<Drug> req=formerD;

    @Override
      public void initialize(URL location, ResourceBundle resources) {


        initCol();
        loadData();
        eventTrigger();

    }
    public void initCol(){
        code_C.setCellValueFactory(new PropertyValueFactory<>("code"));
        name_C.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_C.setCellFactory(cellController.CCellFactory("drug","type"));
        doss_C.setCellFactory(cellController.subCellFactory("drug","dose"));
        menu_C.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."}));
        notice_C.setCellFactory(cellController.BCellFactory(new showButton("show")));
    }
    public void  loadData(){
        try{
            req.onReceive(v-> drug_table.setItems(req.respond));

            requestD.put(req.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setTableItems(List<Drug> items){
        data.setAll(items);
    }
    public void eventTrigger(){
        cellController.MenuDispatcher.addListener(e-> {
                    IntegerProperty prop= (IntegerProperty) e;
                    if(prop.getValue()==0){
                        System.out.println("delete");
                        requestD.offer(req.remove(drug_table.getItems().get(cellController.index)));

                    }
                }
        );
    }
static public void closePopuUp(){
    s.close();
}
    public void add_drug_table(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_drugs.fxml"));
      Scene sc =new Scene(root);

        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
       s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        s.initStyle(StageStyle.TRANSPARENT);
        s.show();

    }
}
