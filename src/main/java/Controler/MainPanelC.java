package Controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelC  implements Initializable {
    public AnchorPane main_panel;
    public Pane patient_search;
    public Pane quick_panel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void add_quick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dr/patient_search.fxml"));
        Scene sc =new Scene(root);
        sc.setFill(Color.TRANSPARENT);
        Stage s=new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setScene(sc);
        /*  s.initStyle(StageStyle.TRANSPARENT);*/
        s.show();
    }

    public void new_precP(ActionEvent actionEvent)  {
        quick_panel.toFront();
    }
}
