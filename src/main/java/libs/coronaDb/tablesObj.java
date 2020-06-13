package libs.coronaDb;

public class tablesObj {
    private int UUID=0;
    private String tableName="";
    private int size=0;
public tablesObj tablesObj(int UUID,String tableName,int size){
    this.UUID=UUID;
    this.tableName=tableName;
    this.size=size;
    return this;
}
    public int getUUID() {
        return UUID;
    }

    public void setUUID(int UUID) {
        this.UUID = UUID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
