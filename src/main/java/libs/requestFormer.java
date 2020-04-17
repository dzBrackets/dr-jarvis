package libs;

final public class requestFormer<type> {
   public String type;
    public type object;
    public Object canon;
   public String req;
public type[] arguments;
    public requestFormer(String type,type object) {
        this.type = type;
        this.object=object;
    }
    public requestFormer(String type, String req,Object object) {
        this.type = type;
        this.req = req;
        this.canon=object;
    }
    public requestFormer(String type) {
        this.type = type;
    }
    public requestFormer(String type,type[]arguments) {
        this.type = type;
        this.arguments=arguments;
    }


}
