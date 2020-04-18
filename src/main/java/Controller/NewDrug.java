package Controller;

import DataClass.Drug;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import libs.requestFormer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.DrugList.closePopuUp;
import static Controller.DrugList.setTableItems;
import static dr.FinalsVal.*;

public class NewDrug implements Initializable {

    public TextField name_TXF;
    public TextField type_TXF;
    public TextField doss_TXF;
    public JFXTextArea write_TXA;
    public JFXButton add_btn;
    public JFXComboBox<String> weight_combo;
    public Label error_txt;
Drug drug;
private requestFormer<Drug> req=formerD;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> weight_list = FXCollections.observableArrayList("mg","kg","ml","ug");
        weight_combo.setItems(weight_list);
        weight_combo.getSelectionModel().select(0);

    }



    public void add_drug(ActionEvent actionEvent) {
        try {
        boolean update=true;
        String parsedName=removeSpace(name_TXF.getText().toLowerCase());
        String parsedType=removeSpace(type_TXF.getText().toLowerCase());
            if(parsedName.length()>=3){
                if(doss_TXF.getText().length()>0&&isNumeric(doss_TXF.getText())) {
                    if(parsedType.length()>1) {
                        requestD.put(req.find("getName",parsedName));
                        Object obj=respondObj.take();
                        if (!(obj instanceof Drug)){
                            drug=new Drug();
                            drug.setName(parsedName);
                            update=false;
                        }
                        else drug = (Drug) obj;
                        if(!drug.getType().contains(parsedType))
                        drug.getType().add(parsedType);

                        if(!drug.getDose().contains(doss_TXF.getText() + weight_combo.getSelectionModel().getSelectedItem()))
                        drug.getDose().add(doss_TXF.getText() + weight_combo.getSelectionModel().getSelectedItem());

                        //correct
                        if(update)
                            requestD.put(req.update());
                        else{
                            drug.setNumCode(database.updateUUID("drug"));
                            requestD.put(req.post(drug));
                        }
                        setTableItems(respondD.take());
                        closePopuUp();
                    }

                    else
                        error_txt.setText("Type is too short!");

                }
                else
                    error_txt.setText("Wrong dose!");


        }

        else{
            error_txt.setText("name is to short!");}
error_txt.setVisible(true);

        }

        catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
