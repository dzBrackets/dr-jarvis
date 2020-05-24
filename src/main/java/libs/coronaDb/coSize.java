package libs.coronaDb;

public class coSize {
   private String tableName="N/D";
    private int size=-1;

    public void set(String name,int size){
        tableName=name;
        this.size=size;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
