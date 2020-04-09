package model;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Side;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;

public class patient {
    private String first_name ,last_name,last_visite,id;
    private  int age;
    private JFXButton diagnostic ;
    private  MenuButton menu;



    public patient(String first_name, String last_name, String last_visite, String id, int age, JFXButton diagnostic , MenuButton menu) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_visite = last_visite;
        this.id = id;
        this.age = age;
        this.diagnostic = diagnostic;
        this.menu=menu;
        diagnostic.setText("Show");
       diagnostic.getStyleClass().add("show_btn");

        ImageView img1 = new ImageView("dr/image/trash_24px.png");
        ImageView img2 = new ImageView("dr/image/ball_point_pen_24px.png");
        ImageView img4 =new ImageView("dr/image/menu_vertical_24px.png");
        img1.setFitHeight(15);  img1.setFitWidth(15);
        img2.setFitWidth(15); img2.setFitHeight(15);
        MenuItem delete = new MenuItem("Delete ...", img1);
        MenuItem edit = new MenuItem("Edit ...", img2);
        menu.getItems().addAll(delete,edit);
        menu.setText("");
        menu.setGraphic(img4);
        menu.setPopupSide(Side.LEFT);
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_visite() {
        return last_visite;
    }

    public void setLast_visite(String last_visite) {
        this.last_visite = last_visite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JFXButton getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(JFXButton diagnostic) {
        diagnostic = diagnostic;
    }

    public MenuButton getMenu() {
        return menu;
    }

    public void setMenu(MenuButton menu) {
        this.menu = menu;
    }

}
