package dr;
import DataClass.Patient;
import libs.coronaDb.coCollection;
import libs.requestFormer;
import static dr.FinalsVal.respondObj;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import static dr.FinalsVal.database;
public class dataThread<type> extends Thread {
    private coCollection<type> data;
private SynchronousQueue<requestFormer<type>> request;
   private SynchronousQueue<coCollection<type>> respond;
    public dataThread(String name, Class<type> className,SynchronousQueue<requestFormer<type>> request, SynchronousQueue<coCollection<type>> respond) throws IOException, ClassNotFoundException {
        super(name);
        this.respond=respond;
        this.request=request;
        data = database.getCollection(name, className);
        System.out.println("abc");

    }

    public void run() {

        System.out.println("abc");
        while (true) {
            requestFormer<type> req;
            try {
                req = request.take();

            if (req.type.equals("get")) {
                respond.put(data);
            }
            if (req.type.equals("find")){
                List<type> D=data.findByObject(req.req,req.canon);
                if(D.size()>0) {
                    respondObj.put(D.get(0));}
                else respondObj.put(new Object());


            }
            if (req.type.equals("remove")){
                data.removeOne(req.object);
                respond.put(data);
            } ;
            if (req.type.equals("add")) {
                data.insertOne(req.object);

                respond.put(data);

            };
            if (req.type.equals("update")){
                //data.updateOne(req.arguments[0],req.arguments[1]);
                data.reSave();
                respond.put(data);

            } ;

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}


