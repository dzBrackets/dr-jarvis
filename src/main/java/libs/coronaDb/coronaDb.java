package libs.coronaDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class coronaDb {
    private String name;
    static ObjectMapper mapper;
    private ArrayList<String> tables;
    private String dir=new File("storage").getAbsolutePath()+File.separator;
    private String backup=new File("backup").getAbsolutePath()+File.separator;
File conf;
    public coronaDb(String name){
        this.name=name;
        this.tables=new ArrayList<>();
        mapper=new ObjectMapper();
        new File(dir).mkdirs();
        conf=new File(dir+name+".config");

        try {
            loadConfig();
        } catch (IOException e) {
            try {
                conf.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
private void initTab(String table,String fileName) throws IOException{
    tables.add(table);
    saveConfig();
if(!new File(fileName).exists()){
    String jsonString = "[";
            new BufferedWriter(new FileWriter(fileName))
                    .append(jsonString)
                    .close();
}

else{
    File f=new File(backup+table+" "+new Date().toString());
   f.mkdirs();
    Files.copy(Paths.get(fileName),f.toPath(), StandardCopyOption.REPLACE_EXISTING);

    throw new IOException("Something Wrong happen your file has been backedUp in case of loss data.try launch app again and hope good");}
    }

    public <type> coCollection<type> getCollection(String name,Class<type> className) throws IOException, ClassNotFoundException {
        String filePath=dir+name+".json";

        if (!tables.contains(name)) {
            initTab(name,filePath);
            System.out.println("Not Founds! Creating table...");
            return new coCollection<>(name,filePath,className);
        };

        File tempFile=_tempFile(filePath);
        tempFile.deleteOnExit();
        coCollection<type> loadedObject=new coCollection<type>(name,filePath,className);
       String loadedJson = _jsonStringFixer(readFile(filePath));
        loadedObject.addAll(mapper.readValue(loadedJson, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class,className)));

        return loadedObject ;
    }

    private File _tempFile(String filePath) throws IOException {
        File t=File.createTempFile("doctorjarvistempfile",".tmp");
        Files.copy(Paths.get(filePath),t.toPath(), StandardCopyOption.REPLACE_EXISTING);

       new BufferedWriter(new FileWriter(t, true)).append("]").close();
       return t;
    }

    private String readFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath)).get(0);
    }
public void loadConfig() throws IOException {

    if (conf.exists()) {
        Collections.addAll(tables,mapper.readValue(conf,String[].class));
        System.out.println(conf.toString());
    }
    else saveConfig();

}
public void saveConfig() throws IOException {
    System.out.println("save");
    mapper.writeValue(conf,tables);
};
    static public String _jsonStringFixer(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            str += "]";
        }
    return str;
    }
}

