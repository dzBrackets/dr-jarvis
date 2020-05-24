package Controller;

import DataClass.prescriptionsHistory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import libs.cellController;
import libs.requestFormer;
import model.showButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.formerH;
import static dr.FinalsVal.requestH;

public class prescriptionsHistoryC implements Initializable {
    public AnchorPane prescriptionsHistory;

    public TableView<prescriptionsHistory> history_table;
    public TableColumn<prescriptionsHistory,String> presId;
    public TableColumn <prescriptionsHistory,String> userId;
    public TableColumn <prescriptionsHistory,String> date;
    public TableColumn <prescriptionsHistory,String> show_c;
    public prescriptionDetailsC deatailcontroller;
    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
   public libs.cellController<prescriptionsHistory> cellController=new cellController<>();

    private final requestFormer<prescriptionsHistory> req=formerH;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
 initCol();
 loadData();
 initializeHandlers();

    }
    public void initCol(){
        presId.getStyleClass().add("start");
        show_c.getStyleClass().add("end");
        presId.setCellValueFactory(new PropertyValueFactory<>("presId"));
        userId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        show_c.setCellFactory(cellController.BCellFactory(new showButton("Details ...")));
    }
    public void initDashboardController(){

        FXMLLoader loader =new FXMLLoader(getClass().getResource("/dr/FXML/POPUP/prescriptionDetails.fxml"));
        Parent root=null;
        try {
            root= loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
         deatailcontroller = loader.getController();
        deatailcontroller.loadFrom(history_table.getItems().get(cellController.index));
         deatailcontroller.dad(stage);

    }
    public void  loadData(){
cellController.clicked.addListener(v->{

    initDashboardController();

});
        req.onReceive(v->{
            history_table.setItems(req.respond);
        });

        requestH.offer(req.get());

    }
    public void  initializeHandlers(){
        cellController.clicked.addListener(v->{
     /*       File file = new File("chart.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(MainPanelC.getTemplateSnap(), null), "png", file);
            } catch (IOException e) {
                System.out.println(e);
            }
            File x = new File("example.pdf");

            PdfWriter writer = null;
            try {
                writer = new PdfWriter(x);
            } catch (FileNotFoundException e) {
               // e.printStackTrace();
            }
            assert writer != null;
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            String imFile = file.getAbsolutePath();
            ImageData data = null;
            try {
                data = ImageDataFactory.create(imFile);
            } catch (MalformedURLException e) {
           //     e.printStackTrace();
            }
            Image image = new Image(data);
            document.add(image);
            document.close();
*/

//pdf
/*
            PDDocument doc    = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            PDImageXObject pdimage;
            PDPageContentStream content;
            try {
                pdimage = PDImageXObject.createFromFile(file.getAbsolutePath(),doc);
                content = new PDPageContentStream(doc, page);
                content.drawImage(pdimage, 0, 0);
                content.close();
                doc.addPage(page);
                doc.save("pdf_file.pdf");
                doc.close();
                //file.delete();
            } catch (IOException ex) {
ex.getStackTrace();
            }*/
         //

        });
    }



}
