package Controller;

import DataClass.customizable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.jfoenix.controls.*;
import dr.Main;
import dr.async;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import libs.doTheFile;
import libs.requestFormer;
import model.components.drugItem;
import model.components.prevBoxComponent;
import model.usedDrug;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;
import static dr.FinalsVal.customAttrs;
import static libs.helper.byteString;
import static libs.helper.byteStringUTF;

public class settingC implements Initializable {
    public Label tab_type;
    public JFXButton import_btn;
    public JFXButton export_btn;
    public JFXButton cancel_btn;
    public JFXButton save_btn;
    public JFXTextField name_field;
    public JFXTextField email_field;
    public JFXTextField adr_field;
    public JFXTextField phone_field;
    public GridPane fields_gridpane;
    public AnchorPane setting_pane;
    public Tab personal_tab;
    public JFXButton edit_btn;
    public GridPane labels_gridpane;
    public Label namel_label;
    public Label email_label;
    public Label phone_label;
    public Label adr_label;
    public JFXButton load_file_btn;
    public JFXButton load_api_btn;
    public JFXButton buy_btn;
    public JFXButton clean_btn;
    public Tab Preferences_tab;
    public JFXComboBox timezone;
    public JFXComboBox timeformat;
    public Tab customize_tab;
    static public Tab Scustomize_tab;

       static async alpha=new async();
    public GridPane template_gridpane;
    public ArrayList<Pane> list ;
    public AnchorPane choise_pan;
    public AnchorPane edit_pan;
    public AnchorPane load_anchorpane;
    public GridPane textFieldGrid;
    public JFXColorPicker primaryColor;
    public JFXColorPicker secondaryColor;
    public JFXButton save_customise_btn;
    public JFXTabPane tabpane;
  static  public JFXTabPane Stabpane;
    int selectedTempIndex=-1;
    requestFormer<customizable> req=new requestFormer<>();
   public static async beta=new async();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stabpane=tabpane;
        Scustomize_tab=customize_tab;
alpha.onReceive(v-> Platform.runLater(this::setDocInfo));
beta.onReceive(v-> Platform.runLater(this::fetchTemps));
export_btn.setOnMouseClicked(v->{
    database.export("backup/zipzpy.commons");
});
req.onReceive(v-> Platform.runLater(this::loadTemplatesGrid));
showTemplateList();
fetchTemps();
        }
void showEditTemplatePane(){
    choise_pan.setVisible(false);
    edit_pan.setVisible(true);
}
void showTemplateList(){
    choise_pan.setVisible(true);
    edit_pan.setVisible(false);
}
    private void fetchTemps() {
        requestT.offer(req.get());
    }


    void loadTemplatesGrid(){
       template_gridpane.getChildren().clear();
    for (int i = 0; i <req.respond.size(); i++) {
        prevBoxComponent cs = new prevBoxComponent();
        customizable fin = req.respond.get(i);
        if(local_data.getSelectedTemplate()==i){
            cs.selected();
            int finalI = i;
            cs.edit.setOnAction(v->{
                System.out.println("edit");
                 selectedTempIndex = finalI;
                openEditTempPane(fin);
            });
        }
        else{
            cs.notSelected();
            int finalI1 = i;
            cs.use.setOnAction(v->{
                selectedTempIndex = finalI1;
                openEditTempPane(fin);
            });
        }
        template_gridpane.add(cs,i,0);


    }
    prevBoxComponent cs = new prevBoxComponent();
    cs.lastOne();
    cs.add.setOnAction(v->{
        System.out.println("add");
        customizable customAttrs=new customizable();

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("templates Files", "*.dtem"));
        File selectedFile = fileChooser.showOpenDialog(Main.staticstage);



        if(selectedFile!=null) {
        try {
            doTheFile df=new doTheFile(selectedFile);
            df.execute();

            customAttrs=df.config;
            requestT.offer(req.post(customAttrs));
           // System.out.println(df.config.getTemplateId());

        } catch (Exception e) {
            e.printStackTrace();
        }



        }



    });
    template_gridpane.add(cs,template_gridpane.getChildren().size(),0);


}

void openEditTempPane(customizable setting){
boolean stop=false;
    FXMLLoader loader = new FXMLLoader();
    try {
        loader.setLocation(setting.URL());
        loader.load();
    } catch (IOException e) {
        if(req.respond.size()==1&&selectedTempIndex==0) {
            loader.setLocation(getClass().getResource("/dr/FXML/PAGES/template.fxml"));
            try {
                loader.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else{
            requestT.offer(req.remove(req.respond.get(selectedTempIndex)));
            stop=true;}
    }
    if(!stop) {
        templateC controller = loader.getController();
        controller.container.getTransforms().add(new Scale(0.74, 0.74));
        load_anchorpane.getChildren().setAll(controller.container);
        controller.setLabelBy(req.respond.get(selectedTempIndex).getAttributes());

        controller.setPrimaryColor(Color.web(req.respond.get(selectedTempIndex).getAttribute("c1")));
        controller.setSecondaryColor(Color.web(req.respond.get(selectedTempIndex).getAttribute("c2")));
        primaryColor.setValue(Color.web(req.respond.get(selectedTempIndex).getAttribute("c1")));
        secondaryColor.setValue(Color.web(req.respond.get(selectedTempIndex).getAttribute("c2")));

        controller.drug_list.getChildren().clear();
        controller.drug_list.getChildren().add(new drugItem(new usedDrug(), secondaryColor.getValue()));


        primaryColor.setOnAction(v -> {
            controller.setPrimaryColor(primaryColor.getValue());
        });


        secondaryColor.setOnAction(v -> {
            controller.setSecondaryColor(secondaryColor.getValue());
            controller.drug_list.getChildren().clear();
            controller.drug_list.getChildren().add(new drugItem(new usedDrug(), secondaryColor.getValue()));
        });

        for (int i = 0; i < textFieldGrid.getChildren().size(); i++) {
            JFXTextField tf = (JFXTextField) textFieldGrid.getChildren().get(i);
            int finalI = i;
            tf.setText(req.respond.get(selectedTempIndex).getAttribute("tbl" + (i + 1)));

            tf.setOnKeyTyped(v -> {
                controller.setLabelByPos(finalI, tf.getText());
            });

        }

        save_customise_btn.setOnAction(v -> {
            customizable tmp = req.respond.get(selectedTempIndex);

            for (int i = 0; i < textFieldGrid.getChildren().size(); i++) {
                JFXTextField tf = (JFXTextField) textFieldGrid.getChildren().get(i);
                if (tf.getText().length() > 0)
                    tmp.addAttribute("tbl" + (i + 1), tf.getText());
            }
            tmp.addAttribute("c1", primaryColor.getValue().toString());
            tmp.addAttribute("c2", secondaryColor.getValue().toString());

            local_data.setSelectedTemplate(selectedTempIndex);
            requestU.offer(formerU.update());
            requestT.offer(formerT.update());
            controller.drug_list.getChildren().clear();


            showTemplateList();
        });
        showEditTemplatePane();

    }
}
     void setDocInfo(){

        namel_label.setText(local_data.getName());
        email_label.setText(local_data.getEmail());
        adr_label.setText(local_data.getAddress());
        phone_label.setText(local_data.getPhone());
    }

    public void personal_selected(Event event) {
        tab_type.setText("Personal ");
    }

    public void preferences_selected(Event event) {
        tab_type.setText("Preferences ");
    }

    public void customize_selected(Event event) {
        tab_type.setText("customize ");
        loadTemplatesGrid();


    }

    public void infoTab_selected(Event event) {
        tab_type.setText("info");
    }

    public void edit_doc(ActionEvent actionEvent) {
        edit_btn.setVisible(false);
        save_btn.setVisible(true);
        cancel_btn.setVisible(true);
        labels_gridpane.setVisible(false);
        fields_gridpane.setVisible(true);
        name_field.setText(namel_label.getText());
        email_field.setText(email_label.getText());
        adr_field.setText(adr_label.getText());
        phone_field.setText(phone_label.getText());
    }

    public void cancel_edit(ActionEvent actionEvent) {
        fields_gridpane.setVisible(false);
        labels_gridpane.setVisible(true);
        save_btn.setVisible(false);
        cancel_btn.setVisible(false);
        edit_btn.setVisible(true);
    }

    public void save_edit(ActionEvent actionEvent) {

        namel_label.setText(name_field.getText());
        email_label.setText(email_field.getText());
        adr_label.setText(adr_field.getText());
        phone_label.setText(phone_field.getText());
        local_data.setName(name_field.getText());
        local_data.setEmail(email_field.getText());
        local_data.setAddress(adr_field.getText());
        local_data.setPhone(phone_field.getText());

        save_btn.setVisible(false);
        cancel_btn.setVisible(false);
        edit_btn.setVisible(true);
        fields_gridpane.setVisible(false);
        labels_gridpane.setVisible(true);
        requestU.offer(formerU.update());

    }



}
