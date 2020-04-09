package model;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;

public class drug {
    private  String name ,type,doss,code;
    private JFXButton notice;
    private MenuButton menu ;

    public drug(String name, String type, String doss, String code, JFXButton notice, MenuButton menu) {
        this.name = name;
        this.type = type;
        this.doss = doss;
        this.code = code;
        this.notice = notice;
        this.menu = menu;
        notice.setText("Show");
        notice.getStyleClass().add("show_btn");
        ImageView img1 = new ImageView("dr/image/trash_24px.png");
        ImageView img2 = new ImageView("dr/image/ball_point_pen_24px.png");
        ImageView img3 = new ImageView("dr/image/add_32px.png");
        ImageView img4 =new ImageView("dr/image/menu_vertical_24px.png");
        img1.setFitHeight(15);  img1.setFitWidth(15);
        img2.setFitWidth(15); img2.setFitHeight(15);
        img3.setFitWidth(15); img3.setFitHeight(15);
        MenuItem delete = new MenuItem("Delete ...", img1);
        MenuItem edit = new MenuItem("Edit ...", img2);
        MenuItem add_p = new MenuItem("Add new Perception...",img3);
        menu.getItems().addAll(delete,edit,add_p);
        menu.setText("");
        menu.setGraphic(img4);
        menu.setPopupSide(Side.LEFT);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoss() {
        return doss;
    }

    public void setDoss(String doss) {
        this.doss = doss;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JFXButton getNotice() {
        return notice;
    }

    public void setNotice(JFXButton notice) {
        this.notice = notice;
    }

    public MenuButton getMenu() {
        return menu;
    }

    public void setMenu(MenuButton menu) {
        this.menu = menu;
    }



}
