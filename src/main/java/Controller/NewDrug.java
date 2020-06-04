package Controller;

import DataClass.Drug;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import libs.requestFormer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.DrugList.closePopuUp;
import static Controller.DrugList.setTableItems;
import static dr.FinalsVal.*;

public class NewDrug implements Initializable {

    public JFXTextField name_TXF;
    public JFXTextField type_TXF;
    public JFXTextField doss_TXF;
    public JFXTextArea write_TXA;
    public JFXButton add_btn;
    public JFXComboBox<String> weight_combo;
    public Label error_txt;
    public JFXButton Cancel_btn;
    Drug drug;
    private final requestFormer<Drug> req=formerD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        form_validation();
        ObservableList<String> weight_list = FXCollections.observableArrayList("mg","kg","ml","ug");
        weight_combo.setItems(weight_list);
        weight_combo.getSelectionModel().select(0);

    }



    public void add_drug(ActionEvent actionEvent) {
        String parsedName=removeSpace(name_TXF.getText().toLowerCase());
        String parsedType=removeSpace(type_TXF.getText().toLowerCase());
        boolean valid=doss_TXF.validate()&&name_TXF.validate()&&type_TXF.validate();
        System.out.println(valid);
                req.onReceive(v->
                {
                    boolean update=true;
                    if(req.respondObject==null){
                        drug = new Drug();
                        drug.setName(parsedName);
                        try {
                            drug.setNumCode(database.updateUUID("drug"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        update = false;
                    }

                    else {drug = req.respondObject;
                    }
                    if (!drug.getType().contains(parsedType))
                        drug.getType().add(parsedType);

                    if (!drug.getDose().contains(doss_TXF.getText() + weight_combo.getSelectionModel().getSelectedItem()))
                        drug.getDose().add(doss_TXF.getText() + weight_combo.getSelectionModel().getSelectedItem());
               if(valid){
                   drug.setNotice(write_TXA.getText());
                   if (update){
                        requestD.offer(req.update());
                    }
                    else{
                        requestD.offer(req.post(drug));
                    }
                   req.respondObject=null;

               }
                }

        );

        requestD.offer(req.find("getName", parsedName));
      if(valid)
        closePopuUp();
else {

          name_TXF.validate();
          type_TXF.validate();
          doss_TXF.validate();
      }


    }
    public void form_validation(){
        name_TXF.setFocusTraversable(false);
        type_TXF.setFocusTraversable(false);
        doss_TXF.setFocusTraversable(false);
        RequiredFieldValidator name_req =new RequiredFieldValidator();
        RequiredFieldValidator type_req=new RequiredFieldValidator();
        RequiredFieldValidator doss_req=new RequiredFieldValidator();
        name_req.setMessage("Please fill out this Field !");
        type_req.setMessage("Please fill out this Field !");
        doss_req.setMessage("Put a number !");
        name_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        type_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        doss_req.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        /******************************************************************************/
        RegexValidator name_val=new RegexValidator();
        RegexValidator type_val =new RegexValidator();
        NumberValidator doss_val =new NumberValidator();
        name_val.setMessage("Please provide a Valid name !");
        type_val.setMessage("Please provide a Valid input !");
        doss_val.setMessage("Put a number !");
        name_val.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        type_val.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        doss_val.setIcon(new ImageView("dr/image/icons8_box_important_16.png"));
        name_val.setRegexPattern("[a-z]{1,10}");
        type_val.setRegexPattern("[a-z]{1,10}");
        /*******************************************************************************/
        name_TXF.getValidators().addAll(name_req,name_val);
        type_TXF.getValidators().addAll(type_req,type_val);
        doss_TXF.getValidators().addAll(doss_req,doss_val);

        name_TXF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                name_TXF.validate();
            }}
        });
        type_TXF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                type_TXF.validate();
            }}
        });
        doss_TXF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                doss_TXF.validate();
            }}
        });
    }

    public void Cancel(ActionEvent actionEvent) {
        DrugList.add_drug_from_stage.close();
        MainPanelC.effect.setRadius(0);
    }
}
