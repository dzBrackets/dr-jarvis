package Controller;

import DataClass.Patient;
import DataClass.prescriptionsHistory;
import dr.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import libs.cellController;
import libs.requestFormer;
import model.popUpWindow;
import model.showButton;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dr.FinalsVal.*;

public class prescriptionsHistoryC implements Initializable {
    public AnchorPane prescriptionsHistory;

    public TableView<prescriptionsHistory> history_table;
    public TableColumn<prescriptionsHistory,String> presId;
    public TableColumn <prescriptionsHistory,String> userId;
    public TableColumn <prescriptionsHistory,String> date;
    public TableColumn <prescriptionsHistory,String> show_c;

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
    public void  loadData(){

        req.onReceive(v->{
            history_table.setItems(req.respond);
        });

        requestH.offer(req.get());

    }
    public void  initializeHandlers(){
        cellController.clicked.addListener(v->{
            File file = new File("chart.png");




            try {

                ImageIO.write(SwingFXUtils.fromFXImage(MainPanelC.getTemplateSnap(), null), "png", file);
            } catch (IOException e) {
                System.out.println(e);
            }

//pdf

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
            }
         //

        });
    }
}
