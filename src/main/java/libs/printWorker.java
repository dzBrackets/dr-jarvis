package libs;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

import javax.print.PrintException;

public class printWorker {



    static public void print(final Node node) throws PrintException {

        node.setVisible(true);
        Printer printer = Printer.getDefaultPrinter();
        if (printer==null) throw new PrintException("Default printer not found");
        System.out.println(printer.getName());
        printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        // System.out.println( "w:"+((pageLayout.getPrintableWidth()/72)*96) );
        //System.out.println( "h:"+((pageLayout.getPrintableHeight()/72)*96));

        System.out.println( "docw:"+node.getBoundsInParent().getWidth());
        System.out.println( "doch:"+node.getBoundsInParent().getHeight());

        //   double scaleX = ((pageLayout.getPrintableWidth()/72)*96) /node.getBoundsInParent().getWidth();
        //   double scaleY = ((pageLayout.getPrintableHeight()/72)*96) /node.getBoundsInParent().getHeight();

        node.getTransforms().add(new Scale(0.95, 0.95));

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        boolean success = job.printPage(node);
        if (success) {
            job.endJob();
        }
        node.setVisible(false);

    }

}
