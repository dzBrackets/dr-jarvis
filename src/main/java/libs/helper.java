package libs;

import Controller.MainPanelC;
import DataClass.Patient;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.components.drugItem;
import model.usedDrug;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static libs.printWorker.print;

public class helper {
   static public void printWithData(Patient selectedPatient, List<usedDrug> data) throws printerException {


       MainPanelC.templateController.setTemplateInfo(selectedPatient);
       int i = 0, j = 0;
       while (i < data.size()) {
           MainPanelC.templateController.drug_list.add(new drugItem(data.get(i), MainPanelC.templateController.secColor), 0, j);
           j++;
           i++;
           if (i % 5 == 0) {
               print(MainPanelC.templateStatic);
               MainPanelC.templateController.reset();
               MainPanelC.templateController.setTemplateInfo(selectedPatient);

               j = 0;
           }

       }
           print(MainPanelC.templateStatic);
       MainPanelC.templateController.reset();


   }
    static public String colorToRgba(Color color){
        return String.format("rgba(%d, %d, %d, %f)",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()),
                color.getOpacity());
    }
  /*
    static public String byteString(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);

    }
    static public String byteStringUTF(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);


    }

   */
    static public Window getCurrentStage(){
        if((Stage.getWindows().stream().filter(Window::isShowing).collect(Collectors.toList()).get(1))!=null)
        return Stage.getWindows().stream().filter(Window::isShowing).collect(Collectors.toList()).get(1);

        return Stage.getWindows().stream().filter(Window::isShowing).collect(Collectors.toList()).get(0);
    }

    public static void printWithData(Patient selectedPatient, ObservableList<usedDrug> data, JFXButton exit_btn) {

        MainPanelC.templateController.setTemplateInfo(selectedPatient);
        int i = 0, j = 0;
        while (i < data.size()) {
            MainPanelC.templateController.drug_list.add(new drugItem(data.get(i), MainPanelC.templateController.secColor), 0, j);
            j++;
            i++;
            if (i % 5 == 0) {
                print(MainPanelC.templateStatic,exit_btn);
                MainPanelC.templateController.reset();
                MainPanelC.templateController.setTemplateInfo(selectedPatient);

                j = 0;
            }

        }
        print(MainPanelC.templateStatic,exit_btn);
        MainPanelC.templateController.reset();


    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void movableFalse(TableView tableView) {
        List<TableColumn<?, ?>> columns = new ArrayList(tableView.getColumns());
        tableView.getColumns().addListener(new ListChangeListener()
        {
            public boolean suspended;
            @Override public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true; tableView.getColumns().setAll(columns);
                    this.suspended = false; } } }); }
}
