package dr;
import libs.requestFormer;

import java.io.IOException;
import java.util.ArrayList;
import static dr.FinalsVal.*;
public class dataThread<type> extends Thread {
    private ArrayList<type> data;

    public dataThread(String name, Class<type> className) throws IOException, ClassNotFoundException {
        super(name);
        data = database.getCollection("drug", className);

    }

    public void run() {


        while (true) {
            requestFormer req;
            try {
                req = request.take();

            if (req.type.equals("get")) {
                respond.put(data);
            }
            if (req.type.equals("find")){

            } ;
            if (req.type.equals("remove")) ;
            if (req.type.equals("append")) ;
            if (req.type.equals("post")) ;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


