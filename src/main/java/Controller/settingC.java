package Controller;

import DataClass.customizable;
import com.jfoenix.controls.*;
import dr.Main;
import dr.async;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import libs.updateTask;
import model.components.amazingDialog;
import model.components.drugItem;
import model.components.prevBoxComponent;
import model.components.spawnButton;
import model.usedDrug;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dr.FinalsVal.*;
import static dr.Main.mainStage;
import static libs.env.APP_FILE_NAME;
import static libs.env.APP_VERSION;

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
    public Label version_lbl;

    int selectedTempIndex=-1;
    requestFormer<customizable> req=new requestFormer<>();
   public static async beta=new async();
    amazingDialog alr=null;
   boolean updHided=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        version_lbl.setText(APP_VERSION);
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
        alr=new amazingDialog();
        clean_btn.setOnAction(v->{
            try{
            if(updHided){
                alr.show(clean_btn.getScene().getWindow());
                alr.stageFollowHide(true);
            }
            else
                 updateSoftwareEvent();
            }
            catch (NullPointerException e){
                System.out.println("hemm");
            }

        });
        }
        void updateSoftwareEvent(){
                alr=new amazingDialog();
                updateTask updater=new updateTask();
                Thread exes=new Thread(updater);
             updater.exceptionProperty().addListener(vx->{
                updater.getException().printStackTrace();
});
                alr.setTitle("Update");
                alr.setPosition(300,300);

            JFXButton cancel = spawnButton.red("Cancel");
            JFXButton hide = spawnButton.gray("hide");
            hide.setOnAction(vx->{
                alr.close();
                alr.stageFollowHide(false);
                updHided=true;
            });
                alr.getButtonList().setAll(cancel);
                alr.contentProperty().bind(updater.messageProperty());
                cancel.setOnAction(vv-> {
                    updater.cancel();
                    alr.stageFollowHide(false);
                    alr.close();
                    updHided=false;

                });



                JFXButton update = spawnButton.green("Update...");
                JFXButton install = spawnButton.green("Install...");
                JFXButton features = spawnButton.blue("What's news...");
                features.setPrefWidth(100);
                install.setOnAction(vs->{


                        alr.contentProperty().unbind();
                        alr.progress(false);
                        alr.setContent("the Software will be reboot please wait...");
                        alr.getButtonList().clear();
                        new Thread(() -> {
                            String u_type = updater.ver.updateType == 1 ? "fuckyouhomie" : "ezypizy";
                            System.out.println(u_type);
                            String javaExe="java";
                            if (new File("lib").exists())
                                javaExe=new File("lib/bin/java.exe").getAbsolutePath();

                                try {
                                System.out.println(javaExe);
                                Runtime.getRuntime().exec(javaExe+" -jar bin/updater.jar "+u_type+" "+APP_FILE_NAME);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            System.exit(0);}
                            ).start();
                });
                features.setOnAction(vs->{
                    alr.setTitle("Update version :"+updater.ver.versionNumber);
                    alr.contentProperty().unbind();
                    alr.setContent(updater.ver.versionName+" features :\n"+updater.ver.versionNews);
                   alr.getButtonList().remove(features);
                });
                update.setOnAction(vs->{
                    alr.getButtonList().removeAll(update,features);
                    alr.getButtonList().add(hide);
                    alr.titleProperty().bind(updater.titleProperty());
                    alr.contentProperty().bind(updater.messageProperty());
                    alr.progressProperty().bind(updater.progressProperty());
                    updater.updateUpdateState(5);
                });

                updater.updateState.addListener(vs->{
                    if(updater.getUpdateState()==1){
                        Platform.runLater(()->alr.getButtonList().addAll(update,features));
                    }
                    if(updater.getUpdateState()==2){
                        Platform.runLater(()->alr.getButtonList().setAll(cancel));

                    }
                        }
                );
updater.workDoneProperty().addListener(xv->{

    if(updater.getTotalWork()==updater.getWorkDone()){
        if(updHided)
            alr.show(clean_btn.getScene().getWindow());
    alr.getButtonList().setAll(install,cancel);}
});


                alr.show(clean_btn.getScene().getWindow());
                alr.stageFollowHide(true);
                exes.start();

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
        File selectedFile = fileChooser.showOpenDialog(mainStage);



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
        //controller.configTemplate(setting);
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
