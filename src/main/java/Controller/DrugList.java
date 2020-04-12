package Controller;

import DataClass.Drug;
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
import java.util.ResourceBundle;
import static dr.FinalsVal.*;
public class DrugList  implements Initializable {
    public TableView<Drug> drug_table;
    public TableColumn<Drug,String>  code_C;
    public TableColumn<Drug,String> name_C;
    public TableColumn<Drug,String>  type_C;
    public TableColumn<Drug,String>  doss_C;
    public TableColumn<Drug, String>  notice_C;
    public TableColumn<Drug, String> menu_C;

    cellController<Drug> cellController = new cellController<>();


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
        doss_C.setCellFactory(cellController.CCellFactory("drug","dose"));
        menu_C.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."}));
        notice_C.setCellFactory(cellController.BCellFactory(new showButton("show")));
    }
    public void  loadData(){
        ObservableList<Drug> data = FXCollections.observableArrayList();
        try {
            requestD.put(new requestFormer<>("get","",null));
            coCollection<Drug> dList=respondD.take();
            System.out.println(dList.size());
            data.addAll(dList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        drug_table.setItems(data);

    }
    public void setTableItems(coCollection<Drug> items){
        ObservableList<Drug> data = FXCollections.observableArrayList();
               data.addAll(items);
        drug_table.setItems(data);
    }
    public void eventTrigger(){
cellController.MenuDispatcher.addListener(e-> {
           IntegerProperty prop= (IntegerProperty) e;
    if(prop.getValue()==0){
        System.out.println("delete");
        try {
            requestD.put(new requestFormer<>("remove","",drug_table.getItems().get(cellController.index)));
            setTableItems(respondD.take());

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
    );
    }

    public void add_drug_table(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/FXML/POPUP/New_drugs.fxml"));
        Scene sc =new Scene(root);

        sc.setFill(Color.TRANSPARENT);
        sc.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        Stage s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        s.initStyle(StageStyle.TRANSPARENT);
        s.show();
    }
}
