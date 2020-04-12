package libs;

final public class requestFormer<type> {
   public String type;
   public type object;
   public String req;

    public requestFormer(String type, String req,type object) {
        this.type = type;
        this.req = req;
        this.object=object;
    }

}
