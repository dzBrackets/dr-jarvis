package model;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.SplitMenuButton;

public class patient {
    private String first_name ,last_name,last_visite,id;
    private  int age;
    private JFXButton diagnostic ;
    private  SplitMenuButton menu;



    public patient(String first_name, String last_name, String last_visite, String id, int age, JFXButton diagnostic , SplitMenuButton menu) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.last_visite = last_visite;
        this.id = id;
        this.age = age;
        this.diagnostic = diagnostic;
        this.menu=menu;
        diagnostic.setText("Show");
       diagnostic.getStyleClass().add("show_btn");
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
    public SplitMenuButton getMenu() {
        return menu;
    }

    public void setMenu(SplitMenuButton menu) {
        this.menu = menu;
    }

}
