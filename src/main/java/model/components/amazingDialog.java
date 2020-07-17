package model.components;

import Controller.alertBox;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import model.popUpWindow;

import java.io.IOException;

public class amazingDialog {

    public alertBox self;
    public popUpWindow pop;
    public static final String WARNING="warning";
    public static final String INFO="info";
    public static final String TOP_LEFT="tl";
    public static final String TOP_RIGHT="tr";
    public static final String BOTTOM_LEFT="bl";
    public static final String BOTTOM_RIGHT="br";
    private Window stage;

    public amazingDialog(){
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

        autoHide(false);
        blackBack(false);

    }
    public amazingDialog(alertBox controller){
        super();
        self = controller;
        self.general.getChildren().remove(self.blackHover);
        setBubbleDir("none");
    }



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
        if(type.equals(WARNING)) {
            Image error = new Image("dr/image/error_24px.png");
            self.icon.setImage(error);
        }
    }
    public void centerOf(Window stage){
        self.container.setLayoutX(stage.getX()/2);
        self.container.setLayoutY(stage.getY()/2);
    }
public void setBubbleDir(String pos){
    if(pos.equals(TOP_LEFT)){
        self.ptl.setVisible(true);
        self.pbr.setVisible(false);
        self.pbl.setVisible(false);
        self.ptr.setVisible(false);
    }
    if(pos.equals(TOP_RIGHT)){
        self.ptl.setVisible(false);
        self.pbr.setVisible(false);
        self.pbl.setVisible(false);
        self.ptr.setVisible(true);
    }
    if(pos.equals(BOTTOM_LEFT)){
        self.ptl.setVisible(false);
        self.pbr.setVisible(false);
        self.pbl.setVisible(true);
        self.ptr.setVisible(false);
    }
    if(pos.equals(BOTTOM_RIGHT)){
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
public void autoHide(boolean v){
    pop.setAutoHide(v);
    pop.setHideOnEscape(false);
}
    public void show(Window stage, Node node)
    {
        this.stage=stage;
        pop.setOnAutoHide(v->close());
        pop.hideOnEscapeProperty().addListener(v->close());
        pop.show(stage,stage.getX(),stage.getY()+53);

        //setBlur(node);

    }
    public void show(Window stage)
    {
        this.stage=stage;
        pop.show(stage,stage.getX(),stage.getY()+53);
        stage.getScene().getRoot().setEffect(new GaussianBlur(3.25));
        stage.getScene().getRoot().setDisable(true);
    }
    public void show(Window stage,boolean blur)
    {
        this.stage=stage;
        pop.show(stage,stage.getX(),stage.getY()+53);
        if(blur){
        stage.getScene().getRoot().setEffect(new GaussianBlur(3.25));
        stage.getScene().getRoot().setDisable(true);}
    }
    public void close(){
        stage.getScene().getRoot().setEffect(null);
        stage.getScene().getRoot().setDisable(false);
        pop.hide();

    }


    public void blackBack(boolean b){
        self.blackHover.setVisible(b);
    }


    public void removeBlur(Pane pane) {
        pane.setEffect(null);
        // blur(false);
    }

    public void setBlur(Node ownerNode) {
        blackBack(false);
        ownerNode.setEffect(new GaussianBlur(3.25));
    }
    public void progress(boolean visible){
        self.progress.setVisible(visible);

    }
    public StringProperty contentProperty(){
        return self.content.textProperty();
    }
    public StringProperty titleProperty(){
        return self.title.textProperty();
    }
    public DoubleProperty progressProperty(){
        self.icon.setVisible(false);
        progress(true);
        return self.progress.progressProperty();
    }
}
