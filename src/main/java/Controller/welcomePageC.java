package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dr.async;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.local_data;

public class welcomePageC implements Initializable {
    public Pane welcome_pane;
    public Pane form_pane;
    public JFXTextField name_field;
    public JFXTextField email_field;
    public JFXTextField adr_field;
    public JFXTextField phone_field;
    public JFXButton second_next_btn;
    public JFXButton skip_btn;
    public JFXButton next_btn;
    public async done=new async();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showWelcome();
next_btn.setOnAction(v->showForm());
second_next_btn.setOnAction(v->{
    if(name_field.getText().length()>0)
        local_data.setName(name_field.getText());
    if(adr_field.getText().length()>0)
        local_data.setAddress(adr_field.getText());
    if(phone_field.getText().length()>0)
        local_data.setPhone(phone_field.getText());
    if(email_field.getText().length()>0)
        local_data.setEmail(email_field.getText());
    GoodToGo();

});
skip_btn.setOnAction(v->GoodToGo());
    }

    private void GoodToGo() {
        done.dispatchEvent();
    }

    void showWelcome(){
        welcome_pane.setVisible(true);
        form_pane.setVisible(false);
    }
    void showForm(){
        welcome_pane.setVisible(false);
        form_pane.setVisible(true);
    }
}
