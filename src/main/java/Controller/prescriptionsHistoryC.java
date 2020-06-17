package Controller;

import DataClass.prescriptionsHistory;
import dr.Main;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import libs.requestFormer;
import model.stageLoader;
import model.showButton;

import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.formerH;
import static dr.FinalsVal.requestH;
import static libs.helper.movableFalse;

public class prescriptionsHistoryC implements Initializable {
    public AnchorPane prescriptionsHistory;

    public TableView<prescriptionsHistory> history_table;
    public TableColumn<prescriptionsHistory,String> presId;
    public TableColumn <prescriptionsHistory,String> userId;
    public TableColumn <prescriptionsHistory,String> date;
    public TableColumn <prescriptionsHistory,String> show_c;
    public prescriptionDetailsC detailController;
    public Spinner<Integer> show_spinner;
    public TextField write_TXF;
   public libs.cellController<prescriptionsHistory> cellController=new cellController<>();

    private final requestFormer<prescriptionsHistory> req=formerH;
    @Override

    public void initialize(URL location, ResourceBundle resources) {
 initCol();
 loadData();
 initializeHandlers();
        movableFalse(history_table);

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

        stageLoader op=new stageLoader("View prescription","/dr/FXML/POPUP/prescriptionDetails.fxml");
        detailController = (prescriptionDetailsC) op.getController();
        op.show();
        detailController.loadFrom(history_table.getItems().get(cellController.index));
        detailController.dad(op.getStage());
    }
    public void  loadData(){
cellController.clicked.addListener(v->{

    initDashboardController();

});
        req.onReceive(v->{
            history_table.setItems(req.respond);
            Platform.runLater(()->
                    (( MainPanelC)Main.sl.getController()).dashController.update());
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
