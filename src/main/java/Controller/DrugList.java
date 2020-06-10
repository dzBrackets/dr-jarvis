package Controller;

import DataClass.Drug;
import com.jfoenix.controls.JFXButton;
import dr.Main;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
import libs.requestFormer;
import model.components.dialog;
import model.components.spawnButton;
import model.popUpWindow;
import model.showButton;
import model.stageLoader;

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
    public TextField write_TXF;
   static ObservableList<Drug> data = FXCollections.observableArrayList();
    static public  Stage add_drug_from_stage;
    public static popUpWindow showNotice;/*create same methode for patientlist and quick panel and i will close them*/
    cellController<Drug> cellController = new cellController<>();
    private requestFormer<Drug> req=formerD;
    double xOffset,yOffset;

    @Override
      public void initialize(URL location, ResourceBundle resources) {


        initCol();
        loadData();
        eventTrigger();

    }
    public void initCol(){
        name_C.getStyleClass().add("start");
        menu_C.getStyleClass().add("end");
        code_C.setCellValueFactory(new PropertyValueFactory<>("code"));
        name_C.setCellValueFactory(new PropertyValueFactory<>("name"));
        type_C.setCellFactory(cellController.CCellFactory("drug","type"));
        doss_C.setCellFactory(cellController.subCellFactory("drug","dose"));
      //  menu_C.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."}));
        menu_C.setCellFactory(cellController.MCellFactory(new String[]{"dr/image/trash_24px.png"},new  String[]{"Delete..."}));
        notice_C.setCellFactory(cellController.BCellFactory(new showButton("show")));
    }
    public void  loadData(){
        try{
            req.onReceive(v-> drug_table.setItems(req.respond));

            requestD.offer(req.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setTableItems(List<Drug> items){
        data.setAll(items);
    }
    public void eventTrigger(){
        cellController.clicked.addListener(v->{
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/show_window.fxml"));
            try {
                Parent root = loader.load();
                show_winC control=loader.getController();
                 showNotice  = new popUpWindow(root.getChildrenUnmodifiable());
                showNotice.show(Main.staticstage);
                control.value_area.setText(drug_table.getItems().get(cellController.index).getNotice());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        cellController.MenuDispatcher.addListener(e-> {
                    IntegerProperty prop= (IntegerProperty) e;
                    if(prop.getValue()==0){
                        System.out.println("delete");
                        dialog check=new dialog();
                        check.setTitle("confirm you choice.");
                        check.setContent("you are going to delete "+drug_table.getItems().get(cellController.index).getName());
                        JFXButton ok = spawnButton.red("Delete");
                        JFXButton cancel = spawnButton.gray("Cancel");
                        check.setPosition(300,300);
                        check.show(Main.staticstage);
                        check.getButtonList().setAll(ok,cancel);
                        ok.setOnAction(v->{
                            requestD.offer(req.remove(drug_table.getItems().get(cellController.index)));
                            cancel.fire();
                        });
                        cancel.setOnAction(v->check.close());

                    }
                }
        );
        write_TXF.textProperty().addListener(v->{
            String value=((StringProperty)v).getValue();
            if(value.length()>1)
            requestD.offer(req.callBack("querySearch","SELECT *","WHERE name $LIKE '"+value+"%'",String.class));
            else
                requestD.offer(req.get());
        });
    }
static public void closePopuUp(){
    add_drug_from_stage.close();
}
    public void add_drug_table(ActionEvent actionEvent) throws IOException {
        stageLoader sl=new stageLoader("Add new drug","/dr/FXML/POPUP/New_drugs.fxml");
         add_drug_from_stage =sl.getStage();
        sl.show();


    }
}
