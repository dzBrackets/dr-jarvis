package libs.coronaDb;

import java.util.ArrayList;

public class tablesObj {
    private int UUID=0;
    private String tableName="";
public tablesObj tablesObj(int UUID,String tableName){
    this.UUID=UUID;
    this.tableName=tableName;
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
}
