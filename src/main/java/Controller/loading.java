package Controller;

import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class loading implements Initializable {

    public JFXSpinner spinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void boost(){
        if(spinner.getProgress()<98)
        spinner.setProgress(spinner.getProgress()+0.002);
    }
}
