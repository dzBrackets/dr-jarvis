package dr;
import libs.coronaDb.coCollection;
import libs.requestFormer;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import static dr.FinalsVal.*;

public class dataThread<type> extends Thread {
    private final coCollection<type> data;
private final SynchronousQueue<requestFormer<type>> request;
    private final SynchronousQueue<coCollection<type>> respond;
    private final SynchronousQueue<List<type>> respondL;

    public dataThread(String name, Class<type> className,SynchronousQueue<requestFormer<type>> request, SynchronousQueue<coCollection<type>> respond,SynchronousQueue<List<type>> respondL) throws IOException, ClassNotFoundException {
        super(name);
        this.respond=respond;
        this.request=request;
        this.respondL= respondL;
        data = database.getCollection(name, className);
    }

    public void run() {

        while (true) {
            requestFormer<type> req;
            try {
                req = request.take();
                System.out.println(3);


            if (req.request.equals(requestFormer.GET)) {
                respond.offer(data);
             //   req.dispatchEvent();
            }


            if (req.request.equals(requestFormer.REMOVE)){
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.removeOne(p);
                req.dispatchEvent();
                respond.offer(data);
                System.out.println(1);

                }

            if (req.request.equals(requestFormer.FIND)){
                List<type> D=data.findByObject((String) req.arg1,req.arg2);
                if(D.size()>0) {
                    respondObj.put(D.get(0));
                }
                else respondObj.put(new Object());
                req.dispatchEvent();
            }

            if (req.request.equals(requestFormer.POST)) {
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.insertOne(p);

                respond.put(data);

                req.dispatchEvent();

            }
            if (req.request.equals(requestFormer.UPDATE)){
                data.reSave();
                respond.put(data);

                req.dispatchEvent();

            }
            if(req.request.equals(requestFormer.CALLBACK))
            {
                if(req.functionName.equals("querySearch")){
                    try {
                        respondL.put(data.querySelector((String)req.arg1,(String)req.arg2).collect());
                        req.dispatchEvent();
                    }
                    catch (QueryParseException|QueryExecutionException e) {
                        e.printStackTrace();
                        System.out.println("abb");
                    }
                }
            }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}


