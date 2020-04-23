package model;

import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;


public class cPopupMenu extends MenuButton {
    public cPopupMenu( String[] imgSource,String[] items){
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
    public cPopupMenu( String[] items,Side side){
        for (int i = 0; i <items.length ; i++) {
            MenuItem menu = new MenuItem(items[i]);
            this.getItems().add(menu);

        }
        this.setText("");
        this.setGraphic(new ImageView("dr/image/menu_vertical_24px.png"));
        this.setPopupSide(side);
    }


}
