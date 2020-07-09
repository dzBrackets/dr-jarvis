package model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.util.Duration;
import model.components.animations;

public class popUpWindow extends Popup{

    public popUpWindow(ObservableList<Node> nodes) {
        getContent().setAll(nodes);
            setAutoHide(true);
        }

        public popUpWindow(Pane pan) {
            getContent().setAll(pan);
            setAutoHide(true);

        }



    public void cleanHide() {
            super.hide();
         //   getContent().clear();
        }

    public void addCloseAnimation(animations easeOut) {
//setAutoHide(false);
        setOnHidden(v->{
       //
        });
    }
}
