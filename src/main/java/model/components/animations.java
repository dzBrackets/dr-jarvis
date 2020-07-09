package model.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class animations {
    public static final String EASE_IN="easeIn";
    public static final String EASE_OUT="easeOut";
    Timeline timeLine=null;

    public animations(Node node,String name) {
        if(name.equals("easeIn")){
        timeLine= new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(node.scaleXProperty(), 0.5)),
                new KeyFrame(Duration.seconds(0.1), new KeyValue(node.scaleXProperty(), 1)),
                new KeyFrame(Duration.ZERO, new KeyValue(node.translateXProperty(), 130d)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(node.translateXProperty(), 0d))

        );

    }
        if(name.equals("easeOut")){
            timeLine = new Timeline(
                    new KeyFrame(Duration.seconds(0.05), new KeyValue(node.scaleXProperty(), 1)),
                    new KeyFrame(Duration.seconds(0.1), new KeyValue(node.scaleXProperty(), 0.5)),
                    new KeyFrame(Duration.ZERO, new KeyValue(node.translateXProperty(), -50d)),
                    new KeyFrame(Duration.seconds(0.1), new KeyValue(node.translateXProperty(), 100))
            );

        }
    }
    public void after(EventHandler<ActionEvent>v){

                timeLine.setOnFinished(v);
    }
   public void playAnimation(){
               timeLine.play();
       }


}
