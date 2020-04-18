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
import java.util.List;
import java.util.stream.Collectors;

public class coronaDb {
    private String name;
    static ObjectMapper mapper;
    private ArrayList<tablesObj> tables;
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
    void createTabFile(String fileName) throws IOException {
        String jsonString = "[";
        new BufferedWriter(new FileWriter(fileName))
                .append(jsonString)
                .close();
    }
private void initTab(String table,String fileName) throws IOException{
    tables.add(new tablesObj().tablesObj(0,table));
    saveConfig();
if(!new File(fileName).exists()){
   createTabFile(fileName);
}

else{
    File f=new File(backup+table+" "+new Date().toString());
   f.mkdirs();
    Files.copy(Paths.get(fileName),f.toPath(), StandardCopyOption.REPLACE_EXISTING);

    throw new IOException("Something Wrong happen your file has been backedUp in case of loss data.try launch app again and hope good");}
    }

    public <type> coCollection<type> getCollection(String name,Class<type> className) throws IOException, ClassNotFoundException {
        String filePath=dir+name+".json";
        coCollection<type> loadedObject=new coCollection<type>(name,filePath,className);
        if (!tables.stream().map(tablesObj::getTableName).collect(Collectors.toList()).contains(name)) {
            initTab(name,filePath);
            System.out.println("Not Founds! Creating table...");
            return loadedObject ;
        };
        if(!new File(filePath).exists()){
            createTabFile(filePath);
            return loadedObject ;
        }

        File tempFile=_tempFile(filePath);
        tempFile.deleteOnExit();
        String loadedJson = _jsonStringFixer(readFile(filePath));
        if (loadedJson.length()<=2){
            createTabFile(filePath);
        return loadedObject;
        }
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
        List<String> l=Files.readAllLines(Paths.get(filePath));
        if(l.size()>0)
        return l.get(0);
        return "";
    }
public void loadConfig() throws IOException {

    if (conf.exists()) {
        Collections.addAll(tables,mapper.readValue(conf,tablesObj[].class));
        System.out.println(conf.toString());
    }
    else saveConfig();

}
public void saveConfig() throws IOException {
    System.out.println("save");
    mapper.writeValue(conf,tables);
};
    static public String _jsonStringFixer(String str) {
        if (str != null && str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        str += "]";
        return str;
    }
    public int getUUID(String tableName){
        return tables.stream().filter(v->(v.getTableName()).equals(tableName)).findFirst().get().getUUID();
    }
    public int updateUUID(String tableName) throws IOException {
       tablesObj tb=tables.stream().filter(v->(v.getTableName()).equals(tableName)).findFirst().get();
       tb.setUUID(tb.getUUID()+1);
       saveConfig();
return tb.getUUID();
    }
}

