package model;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class popupMenu extends MenuButton {
    private final IntegerProperty index=new SimpleIntegerProperty(-1);
    private final ObservableList<MenuItem> itemsObs=FXCollections.observableArrayList();
    private final ContextMenu contextMenu = new ContextMenu();
    private JFXTextField tf=null;
    public popupMenu(String[] imgSource, String[] items){
        for (int i = 0; i <items.length ; i++) {
            ImageView img = new ImageView(imgSource[i]);
            img.setFitWidth(15); img.setFitHeight(15);
            MenuItem menu = new MenuItem(items[i],img);

            this.getItems().add(menu);
        }
        this.setText("");
        this.setGraphic(new ImageView("dr/image/menu_vertical_24px.png"));
        this.setPopupSide(Side.LEFT);
    }
    public popupMenu(){

        itemsObs.addListener((ListChangeListener<? super MenuItem>) v->
        {
            contextMenu.getItems().setAll(itemsObs);
        });
       this.setContextMenu(contextMenu);
        this.setText("");
    }


    public void setItem(List<String> items){
        List<MenuItem>itemMenus=new ArrayList<>();
        for (String item:items) {
            itemMenus.add(new MenuItem(item));
            itemMenus.get(items.indexOf(item)).setOnAction(v->{
                index.set(items.indexOf(item));
            });
        }
        itemsObs.setAll(itemMenus);

    }
    public void bind(JFXTextField tf){
        this.tf=tf;
    }

public void showSuggestion(){
    if (!getContextMenu().isShowing())
        getContextMenu().show(tf,Side.BOTTOM,0,0);
}
public void onHide(){
        index.set(-1);
    getContextMenu().hide();

}
public void onSelect(InvalidationListener v){
        index.addListener(v);

}

}


