package model.components;

import DataClass.Drug;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import model.usedDrug;

public class drugItem extends Pane {
    private Label name,doss,type,qts,notice;
    private Line line ;

    public drugItem(usedDrug drug) {
        name = new Label(drug.getName());
        doss = new Label(drug.getDoss());
        notice = new Label(drug.getNotice());
        qts =new Label(drug.getQts());
        type=new Label(drug.getType());
        line =new Line();
        this.setPrefWidth(100);
        this.setPrefHeight(30);

        setChildStyle();



    }
    public void setChildStyle(){
        this.setPrefWidth(100);
        this.setPrefHeight(30);
      //  name.setLayoutX();//
        name.setLayoutY(6);
        doss.setLayoutX(172);
        doss.setLayoutY(6);
        type.setLayoutX(93);
        type.setLayoutY(6);
        qts.setLayoutX(403);
        qts.setLayoutY(1);
     //   notice.setLayoutX();//
        notice.setLayoutY(35);

        name.setPrefWidth(93);
        name.setPrefHeight(17);
        doss.setPrefWidth(79);
        doss.setPrefHeight(17);
        type.setPrefWidth(79);
        type.setPrefHeight(17);
        notice.setPrefWidth(448);
        notice.setPrefHeight(37);
        qts.setPrefWidth(31);
        qts.setPrefHeight(27);

        name.setFont(Font.font("Marlett",12));
        doss.setFont(Font.font("Marlett",12));
        type.setFont(Font.font("Marlett",12));
        notice.setFont(Font.font("Marlett",12));
        notice.setFont(Font.font("Marlett",13));

        notice.setAlignment(Pos.TOP_LEFT);
        qts.setAlignment(Pos.CENTER);
        line.setEndX(236);
        line.setLayoutX(212);
        line.setLayoutY(73);
        line.setStartX(-212);
        line.setStroke(Color.GREEN);


        this.getChildren().addAll(name,doss,type,notice,qts,line);


    }


}
