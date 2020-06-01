package DataClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class customizable {
    String templateId="0";
    String[] attributes=new String[20];

    public customizable customizable(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }
    @JsonIgnore
    public String getAttribute(int id){
        return attributes[id];
    }
    @JsonIgnore
    public void setAttribute(int id,String value){
    }
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }
}
