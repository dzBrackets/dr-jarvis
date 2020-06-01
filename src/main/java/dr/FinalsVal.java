package dr;

import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import DataClass.*;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableAny;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import libs.coronaDb.coCollection;
import libs.coronaDb.coronaDb;
import libs.requestFormer;
import org.reactivestreams.Subscriber;

final public class FinalsVal {

//       static public final SynchronousQueue<Patient> respond = new SynchronousQueue<>();

       static public final SynchronousQueue<requestFormer<Patient>> requestP = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<userData>> requestU = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Drug>> requestD = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<customizable>> requestT = new SynchronousQueue<>();
       static public final requestFormer<Patient> formerP=new requestFormer<>();
       static public final requestFormer<userData> formerU=new requestFormer<>();
       static public final requestFormer<customizable> formerT=new requestFormer<>();
       static public final requestFormer<Drug> formerD=new requestFormer<>();
       static public final SynchronousQueue<requestFormer<prescriptionsHistory>> requestH = new SynchronousQueue<>();
       static public final requestFormer<prescriptionsHistory> formerH=new requestFormer<>();
       static public final coronaDb database=new coronaDb("norme");
       static public final DateTimeFormatter[] dateFilters={DateTimeFormatter.ofPattern("dd-MM-yyyy h:m"),DateTimeFormatter.ofPattern("dd-MM-yyyy")};
       private final IntegerProperty await =new SimpleIntegerProperty(1);
       static public userData local_data=new userData();
       static public customizable customAttrs=null;
       public static boolean isNumeric(String strNum) {
              if (strNum == null) {
                     return false;
              }
              try {
                     double d = Double.parseDouble(strNum);
              } catch (NumberFormatException nfe) {
                     return false;
              }
              return true;
       }
       public static String removeSpace(String str) {
              String result = "";
              for (int i = 0; i < str.length(); i++){
                     char c = str.charAt(i);
                     if(c!=' ') {
                            result += c;
                     }
              }
              return result;
       }
       static final public void print(final Node node) {

              node.setVisible(true);
              Printer printer = Printer.getDefaultPrinter();
              PageLayout pageLayout = printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
              System.out.println( "w:"+((pageLayout.getPrintableWidth()/72)*96) );
              System.out.println( "h:"+((pageLayout.getPrintableHeight()/72)*96));

              System.out.println( "docw:"+node.getBoundsInParent().getWidth());
              System.out.println( "doch:"+node.getBoundsInParent().getHeight());

              double scaleX = ((pageLayout.getPrintableWidth()/72)*96) /node.getBoundsInParent().getWidth();
              double scaleY = ((pageLayout.getPrintableHeight()/72)*96) /node.getBoundsInParent().getHeight();
              node.getTransforms().add(new Scale(0.95, 0.95));

              PrinterJob job = PrinterJob.createPrinterJob();
              if (job != null) {
                     boolean success = job.printPage(node);
                     if (success) {
                            job.endJob();
                     }
              }
              node.setVisible(false);
       }
}
