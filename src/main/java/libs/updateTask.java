package libs;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import libs.updater.versionInfo;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

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

             ver = requestInfo("1");
            if(ver.isNew()){
                updateUpdateState(1);
                updateMessage("a new update available!");}
            else {
                updateUpdateState(2);

                updateMessage("you have the latest version!");

            }
    while (notYet) {
        Thread.onSpinWait();

    }
    URL url = new URL(ver.url);
    String filename = "file.zip";
    download(url, filename);
    return null;
    }
    public void download(URL url, String fileName) throws IOException {
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
        while (-1 != (n = in.read(buf))) {
            downloaded += n;
            out.write(buf, 0, n);
            updateProgress(downloaded, fileSize);

            updateMessage((df.format(downloaded / (1024F*1024F*8F))) +" MB");
        }
        updateTitle("update has been downloaded successfully.");
        updateMessage("Please click on install to apply the update.");
        out.close();


        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        doTheFile.doFile(new File(fileName));
    }
    public void updateUpdateState(int value){
        updateState.setValue(value);
    }
    public int getUpdateState(){
        return updateState.getValue();
    }
}
