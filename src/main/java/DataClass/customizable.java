package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class customizable implements Serializable {
    private String templateId="0";
    private ArrayList<String>keys=new ArrayList<>();
    private ArrayList<String>attributes=new ArrayList<>();
    private String URL="templates/default.template";
    public customizable customizable(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTemplateId() {
        return templateId;
    }
    @JsonIgnore
    public java.net.URL URL() throws IOException {
        if(!new File(URL).exists())throw new IOException("File dont found");
        return new File(URL).toURI().toURL();
    }
    @JsonIgnore
    public String getAttribute(String key){
        return attributes.get(keys.indexOf(key));
    }
    @JsonIgnore
    public void addAttribute(String key,String value){
        if(keys.contains(key))
            attributes.set(keys.indexOf(key),value);
        else {
            attributes.add(value);
            keys.add(key);
        }
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes=attributes;
    }
}
