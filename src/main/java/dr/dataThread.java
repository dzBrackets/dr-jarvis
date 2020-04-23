package dr;
import DataClass.Patient;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import libs.coronaDb.coCollection;
import libs.requestFormer;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import static dr.FinalsVal.*;

public class dataThread<type> extends Thread {
    private final coCollection<type> data ;
private final SynchronousQueue<requestFormer<type>> request;
    public dataThread(String name, Class<type> className,SynchronousQueue<requestFormer<type>> request) throws IOException, ClassNotFoundException {
        super(name);
        this.request=request;
        data = database.getCollection(name, className);
    }

    public void run() {

        while (true) {
            requestFormer<type> req;
            try {
                req = request.take();
                if (req.request.equals(requestFormer.GET)) {
                    req.reply(data);
                    req.dispatchEvent();
                }



                if (req.request.equals(requestFormer.REMOVE)){
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.removeOne(p);
                req.reply(data);
                req.dispatchEvent();


                }

            if (req.request.equals(requestFormer.FIND)){

                List<type> D=data.findByObject((String) req.arg1,req.arg2);
                if(D.size()>0) {
                    req.reply(D.get(0));
                }
                req.dispatchEvent();

            }

            if (req.request.equals(requestFormer.POST)) {
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.insertOne(p);
                req.reply(data);
                req.dispatchEvent();

            }
            if (req.request.equals(requestFormer.UPDATE)){
                data.reSave();
                req.reply(data);
                req.dispatchEvent();

            }
            if(req.request.equals(requestFormer.CALLBACK))
            {
                if(req.functionName.equals("querySearch")){
                    try {
                        req.respL=(data.querySelector((String)req.arg1,(String)req.arg2).collect());

                        if(req.respL.size()>(int)req.ar3) req.respL=req.respL.subList(0,(int)req.ar3);

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


