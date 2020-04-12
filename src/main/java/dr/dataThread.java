package dr;
import DataClass.Patient;
import libs.coronaDb.coCollection;
import libs.requestFormer;

import java.io.IOException;
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

            } ;
            if (req.type.equals("remove")){
                data.removeOne(req.object);
                respond.put(data);
            } ;
            if (req.type.equals("add")) {
                data.insertOne(req.object);
                respond.put(data);

            };
            if (req.type.equals("post")) ;

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}


