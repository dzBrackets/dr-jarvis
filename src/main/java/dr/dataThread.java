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
   private Class <type> className;
    public dataThread(String name, Class<type> className,SynchronousQueue<requestFormer<type>> request, SynchronousQueue<coCollection<type>> respond) throws IOException, ClassNotFoundException {
        super(name);
        this.respond=respond;
        this.request=request;

        data = database.getCollection(name, className);
this.className=className;
    }

    public void run() {

        System.out.println("abc");
        while (true) {
            requestFormer<type> req;
            try {
                req = request.take();

            if (req.request.equals(requestFormer.GET)) {
                respond.put(data);
            }


            if (req.request.equals(requestFormer.REMOVE)){
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.removeOne(p);
                respond.put(data);
                }

            if (req.request.equals(requestFormer.FIND)){
                List<type> D=data.findByObject((String) req.arg1,req.arg2);
                if(D.size()>0) {
                    respondObj.put(D.get(0));
                }
                else respondObj.put(new Object());


            }

            if (req.request.equals(requestFormer.POST)) {
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.insertOne(p);

                respond.put(data);

            }
            if (req.request.equals(requestFormer.UPDATE)){
                data.reSave();
                respond.put(data);

            }

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}


