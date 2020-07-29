package libs.templateCracker;

public class Holder {
    //types{label,image,patientHolder,DrugListHolder}
    public static final String LABEL="label",IMAGE="img",PATIENT_HOLDER="patHold",DRUGS_HOLD="drugListHolder";
    String type="";
    String name="";
    String style="";
    String value="";
    Double xPos=0.0;
    Double yPos=0.0;
    Double width=0.0;
    Double height=0.0;

    public String getStyle() {
        return style;
    }



    /**
     * A string representation of the CSS style associated with this
     * specific {@code Node}. This is analogous to the "style" attribute of an
     * HTML element. Note that, like the HTML style attribute, this
     * variable contains style properties and values and not the
     * selector portion of a style rule.
     * @param style The inline CSS style to use for this {@code Node}.
     *         {@code null} is implicitly converted to an empty String.
     * @defaultValue empty string
     * @see <a href="doc-files/cssref.html">CSS Reference Guide</a>
     */
    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getXPos() {
        return xPos;
    }

    public void setXPos(Double xPos) {
        this.xPos = xPos;
    }

    public Double getYPos() {
        return yPos;
    }

    public void setYPos(Double yPos) {
        this.yPos = yPos;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", value='" + value + '\'' +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
