package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class customizable {
    String templateId="0";
    ArrayList<String>keys=new ArrayList<>();
    ArrayList<String>attributes=new ArrayList<>();
    String URL="/dr/FXML/PAGES/template.fxml";
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
    public String getAttribute(String key){
        return attributes.get(keys.indexOf(key));

    }
    @JsonIgnore
    public void addAttribute(String key,String value){
        attributes.add(value);
        keys.add(key);

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
