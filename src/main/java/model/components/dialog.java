package model.components;

import Controller.alertBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Window;
import model.popUpWindow;

import java.io.IOException;

public class dialog {

    alertBox self;
    popUpWindow pop;
    public dialog(){
        super();

        Parent root=null;
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/alertBox.fxml"));
        try {
            root= loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        self = loader.getController();
         pop = new popUpWindow(self.general.getChildren());
        setBubbleDir("none");
    //setdialogType("warn");
    }

//    private void setdialogType(String type) {
//    if(warn.equals("warinig"))
//        self.in.set("url")
//    }


    public void setTitle(String str){self.title.setText(str);}
    public void setContent(String str){self.content.setText(str);}
    public ObservableList<Node> getButtonList(){
        return self.buttonList.getChildren();
    }
    public void setPosition(double x, double y){
        self.container.setLayoutX(x);
        self.container.setLayoutY(y);
    }
    public  void setImage(String type){
        if(type.equals("warning")) {
            Image error = new Image("dr/image/error_24px.png");
            self.icon.setImage(error);
        }
    }
public void setBubbleDir(String pos){
    if(pos.equals("tl")){
        self.ptl.setVisible(true);
        self.pbr.setVisible(false);
        self.pbl.setVisible(false);
        self.ptr.setVisible(false);
    }
    if(pos.equals("tr")){
        self.ptl.setVisible(false);
        self.pbr.setVisible(false);
        self.pbl.setVisible(false);
        self.ptr.setVisible(true);
    }
    if(pos.equals("bl")){
        self.ptl.setVisible(false);
        self.pbr.setVisible(false);
        self.pbl.setVisible(true);
        self.ptr.setVisible(false);
    }
    if(pos.equals("br")){
        self.ptl.setVisible(false);
        self.pbr.setVisible(true);
        self.pbl.setVisible(false);
        self.ptr.setVisible(false);
    }
    if(pos.equals("none")){
        self.ptl.setVisible(false);
        self.pbr.setVisible(false);
        self.pbl.setVisible(false);
        self.ptr.setVisible(false);
    }
    }

    public void show(Window staticstage){
       pop.show(staticstage,staticstage.getX(),staticstage.getY()+53);
    }
    public void close(){
        pop.hide();
    }


}
