package libs;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import model.components.amazingDialog;
import model.components.spawnButton;

import static libs.helper.getCurrentStage;

public class printWorker {


    static public void print(final Node node) throws printerException {
        amazingDialog alr=new amazingDialog();
        alr.setPosition(300,300);
        alr.setTitle("Working...");
        alr.blackBack(false);
        alr.show(getCurrentStage());
        JFXButton ok = spawnButton.blue("continue");
        ok.setOnAction(v->alr.close());

        node.setVisible(true);
        Printer printer = Printer.getDefaultPrinter();

        if (printer==null) throw new printerException("Default printer not found");
        alr.setContent("Printer name: "+printer.getName());

        PageLayout page = printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

        System.out.println( "docw:"+node.getBoundsInParent().getWidth());
        System.out.println( "doch:"+node.getBoundsInParent().getHeight());

        Scale A5Scale = new Scale(0.705, 0.705);
        node.getTransforms().add(A5Scale);

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        job.getJobSettings().setPageLayout(page);


        job.jobStatusProperty().addListener((observable, oldValue, newValue) -> {


            System.out.println(newValue);
            alr.setTitle(newValue.toString().toLowerCase());

            alr.setContent("Wait a second...");
            if(newValue== PrinterJob.JobStatus.DONE){
                alr.setTitle("Done!");
                alr.setContent("Your prescription has been send successfully.");
                alr.getButtonList().setAll(ok);
            }
            if(newValue== PrinterJob.JobStatus.CANCELED){
                alr.setTitle("Oophs!");
                alr.setContent("Someone or something canceled your printer job.");
                alr.getButtonList().setAll(ok);
            }
            if(newValue== PrinterJob.JobStatus.ERROR){
                alr.setImage(amazingDialog.WARNING);
                alr.setTitle("Your printer refuse to print!");
                alr.setContent("maybe its time to buy a new one.");
                alr.getButtonList().setAll(ok);
            }
        });

                    boolean success = job.printPage(node);
                    if (success) {
                        job.endJob();
                    }
            node.getTransforms().remove(A5Scale);
            node.setVisible(false);






    }
    static public void print(final Node node,JFXButton btn) throws printerException {
        amazingDialog alr=new amazingDialog();
        alr.setPosition(300,300);
        alr.setTitle("Working...");
        alr.show(getCurrentStage());
        JFXButton ok = spawnButton.blue("continue");
        ok.setOnAction(v->{alr.close();btn.fire();});

        node.setVisible(true);
        Printer printer = Printer.getDefaultPrinter();

        if (printer==null) throw new printerException("Default printer not found");
        alr.setContent("Printer name: "+printer.getName());

        PageLayout page = printer.createPageLayout(Paper.A5, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

        System.out.println( "docw:"+node.getBoundsInParent().getWidth());
        System.out.println( "doch:"+node.getBoundsInParent().getHeight());

        Scale A5Scale = new Scale(0.705, 0.705);
        node.getTransforms().add(A5Scale);

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        job.getJobSettings().setPageLayout(page);


        job.jobStatusProperty().addListener((observable, oldValue, newValue) -> {


            System.out.println(newValue);
            alr.setTitle(newValue.toString().toLowerCase());

            alr.setContent("Wait a second...");
            if(newValue== PrinterJob.JobStatus.DONE){
                alr.setTitle("Done!");
                alr.setContent("Your prescription has been send successfully.");
                alr.getButtonList().setAll(ok);
            }
            if(newValue== PrinterJob.JobStatus.CANCELED){
                alr.setTitle("Oophs!");
                alr.setContent("Someone or something canceled your printer job.");
                alr.getButtonList().setAll(ok);
            }
            if(newValue== PrinterJob.JobStatus.ERROR){
                alr.setImage(amazingDialog.WARNING);
                alr.setTitle("Your printer refuse to print!");
                alr.setContent("maybe its time to buy a new one.");
                alr.getButtonList().setAll(ok);
            }
        });
                    boolean success = job.printPage(node);
                    if (success) {
                        job.endJob();
                    }

           node.getTransforms().remove(A5Scale);
        node.setVisible(false);




    }

}
