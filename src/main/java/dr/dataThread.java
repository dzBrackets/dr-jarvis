package dr;
import DataClass.Patient;
import libs.coronaDb.coCollection;
import libs.requestFormer;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

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
                System.out.println(req.request);
                if (req.request.equals(requestFormer.GET)) {
                    req.reply(data);
                    req.dispatchEvent();
                }

                if (req.request.equals(requestFormer.GET_AMOUNT)) {
                    List<type> accepted;
                    if(data.size()-(int)req.arg1>0)
                        accepted=data.subList(data.size()-(int)req.arg1,data.size());
                    else
                    accepted=data;
                    req.reply(accepted);
                  //  req.reply(accepted.stream().filter(v->!((Patient)v).getPrescriptionsId().isEmpty()).collect(Collectors.toList()));
                    req.dispatchEvent();
                }

                if (req.request.equals(requestFormer.REMOVE)){
                @SuppressWarnings("unchecked")
                type p=(type) req.arg1;
                data.removeOne(p);
                req.reply(data);
                req.dispatchEvent();


                }
                if (req.request.equals(requestFormer.REMOVE_LIST)){
                    System.out.println("remove bunch");
                    for (type p:req.sameTypeList) {
                        data.removeOne(p);
                    }

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
                if (req.request.equals(requestFormer.UPDATE_BY)){
                    @SuppressWarnings("unchecked")
                    type p=(type) req.arg1;
                    data.updateOne(p);
                    req.reply(data);
                    req.dispatchEvent();

                }
            if(req.request.equals(requestFormer.CALLBACK))
            {
                System.out.println("here finaly "+req.functionName);
                try {
                if(req.functionName.equals("querySearch")){
                        req.reply((data.querySelector((String)req.arg1,(String)req.arg2).collect()));
                        if(req.ar3!=null&&req.respond.size()>(int)req.ar3) req.reply(req.respond.subList(0,(int)req.ar3));
                        req.dispatchEvent();
                    }

                    if(req.functionName.equals("mojojojo")){
                        ArrayList<type> selectiveList=new ArrayList<>();
                        for (Object element:req.funArguments)
                        {
                            selectiveList.addAll(data.querySelector("SELECT *",req.arg1+"'"+(String)element+"'").collect());

                        }
                        req.reply(selectiveList);
                        req.dispatchEvent();
                    }
                }
                    catch (QueryParseException|QueryExecutionException e) {
                        e.printStackTrace();
                    }
            }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}


