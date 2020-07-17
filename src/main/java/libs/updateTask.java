package libs;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import libs.updater.versionInfo;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

import static libs.env.APP_VERSION;
import static libs.updater.checker.requestInfo;

public class updateTask extends Task<Void> {
public final SimpleIntegerProperty updateState=new SimpleIntegerProperty(0);


/*
-1:Error
 0:nothing
 1:new update
 2:no update
 5:wake the downloader
* */
public versionInfo ver=null;
    volatile boolean notYet=true;
public updateTask(){
    super();
    updateState.addListener(v->{
if(updateState.getValue()==5){
    notYet=false;
}
    });
}
@Override
    protected Void call() throws Exception {

            updateMessage("Cheking for update...");
    System.out.println("looking");
    try{
             ver = requestInfo(APP_VERSION);}
    catch (Exception e){
        updateUpdateState(-1);
        updateMessage("try again later."+e.getMessage());
    }
    System.out.println("got a reply!");
    if(isCancelled())return null;

    if(ver.isNew()){
                updateUpdateState(1);
                updateMessage("a new update available!");}
            else {
                updateUpdateState(2);

                updateMessage("you have the latest version!");

            }
    while (notYet&&!isCancelled()) {
        Thread.onSpinWait();

    }
    if(isCancelled())return null;

    URL url = new URL(ver.url);
    String filename = "bin/update.zip";
    download(url, filename);
    System.gc();
    return null;
    }
    public void download(URL url, String fileName) throws IOException, InterruptedException {
        updateTitle("Downloading the update:");
        updateMessage("Please wait");
        URLConnection openConnection = url.openConnection();
        int fileSize = openConnection.getContentLength();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        InputStream in = new BufferedInputStream(url.openStream());
        byte[] buf = new byte[5120];
        int n;
        long downloaded = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        while (-1 != (n = in.read(buf))&&!isCancelled()) {
            downloaded += n;
            out.write(buf, 0, n);
            updateProgress(downloaded, fileSize+10000);
            System.out.println("file size: "+fileSize);
            System.out.println("downloaded: "+downloaded);
            updateMessage((df.format(downloaded / (1024F*1024F*8F))) +" MB");

        }
        if(isCancelled())return;

        out.close();
        updateMessage("unpacking...");

        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        doTheFile.doFile(new File(fileName));
        while(!new File(fileName).exists()){

        }
        Thread.sleep(1000);
        updateProgress(fileSize+10000, fileSize+10000);

        updateTitle("update has been downloaded successfully.");
        updateMessage("Please click on install to apply the update.");
    }
    public void updateUpdateState(int value){
        updateState.setValue(value);
    }
    public int getUpdateState(){
        return updateState.getValue();
    }
}
