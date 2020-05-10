package Controller;

import DataClass.Drug;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.components.drugItem;

import java.net.URL;
import java.util.ResourceBundle;

public class templateC implements Initializable {
    public GridPane drug_list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    drug_list.add(new drugItem(new Drug()),0,0);

    }

}
