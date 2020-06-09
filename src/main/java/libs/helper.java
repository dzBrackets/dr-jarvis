package libs;

import Controller.MainPanelC;
import DataClass.Patient;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import model.components.drugItem;
import model.usedDrug;

import javax.print.PrintException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static libs.printWorker.print;

public class helper {
   static public void printWithData(Patient selectedPatient, List<usedDrug> data) throws PrintException {


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

   }
    static public String colorToRgba(Color color){
        return String.format("rgba(%d, %d, %d, %f)",
                (int) (255 * color.getRed()),
                (int) (255 * color.getGreen()),
                (int) (255 * color.getBlue()),
                color.getOpacity());
    }
    static public String byteString(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);

    }
    static public String byteStringUTF(String str){
        byte[] bt = str.getBytes(StandardCharsets.UTF_8);
        return new String(bt);

    }
}
