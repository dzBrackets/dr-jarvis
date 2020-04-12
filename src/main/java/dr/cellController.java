package dr;

import DataClass.Drug;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.cPopupMenu;
import model.showButton;

public class cellController<type>  {

    public int index;
    public IntegerProperty clicked=new SimpleIntegerProperty(-1);
    public StringProperty MenuDispatcher =new SimpleStringProperty("-1");
    Callback<TableColumn<type, String>, TableCell<type, String>> cellFactory;
    public cellController(){
    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> MCellFactory(){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell call(final TableColumn<type, String> param) {
                        final TableCell<type, String> cell = new TableCell<type, String>() {

                            cPopupMenu menu=new cPopupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    menu.getItems().forEach(n->n.setOnAction(event -> {
                                        index=getIndex();
                                        MenuDispatcher.set("-1");
                                        MenuDispatcher.set(n.getText().substring(0,n.getText().length()-3));
                                    }));
                                    setGraphic(menu);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> BCellFactory(JFXButton button){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell call(final TableColumn<type, String> param) {
                        final TableCell<type, String> cell = new TableCell<type, String>() {
                            JFXButton notice = new JFXButton();
                            {
                               notice.setText(button.getText());notice.getStyleClass().addAll(button.getStyleClass());
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    notice.setOnMouseClicked(v->{clicked.setValue((clicked.getValue()+1)%2);});
                                    setGraphic(notice);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> CCellFactory(String dataType,String of){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell call(final TableColumn<type, String> param) {
                        final TableCell<type, String> cell = new TableCell<type, String>() {

                           // cPopupMenu menu=new cPopupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    ComboBox<String> menu=null;
                                    if(dataType.equals("drug")) {
                                        if(of.equals("type"))
                                        menu = new ComboBox<String>(FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getType()));
                                if(of.equals("dose"))
                                        menu = new ComboBox<String>(FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getDose()));
                                    }

                                    assert menu != null;
                                    menu.getSelectionModel().select(0);
                                    setGraphic(menu);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

    }
   

}


/*
        Callback<TableColumn<drug, String>, TableCell<drug, String>> cellFactory = new Callback<TableColumn<drug, String>, TableCell<drug, String>>() {
                    @Override
                    public TableCell call(final TableColumn<drug, String> param) {
                        final TableCell<drug, String> cell = new TableCell<drug, String>() {

        cPopupMenu menu=new cPopupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    menu.getItems().get(1).setOnAction(event -> {
                                        drug drug = getTableView().getItems().get(getIndex());
                                        System.out.println(getIndex());
                                    });
                                    setGraphic(menu);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

 */