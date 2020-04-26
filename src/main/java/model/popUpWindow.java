package model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Popup;

    public class popUpWindow extends Popup{
        public popUpWindow(ObservableList<Node> nodes) {
        getContent().setAll(nodes);
            setAutoHide(true);
        }
}
