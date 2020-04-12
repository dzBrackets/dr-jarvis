package dr;

import java.util.concurrent.SynchronousQueue;

import libs.coronaDb.coronaDb;
import libs.requestFormer;
final public class FinalsVal {
       static final SynchronousQueue<Object> respond = new SynchronousQueue<>();
       static final SynchronousQueue<requestFormer> request = new SynchronousQueue<>();
       static final coronaDb database=new coronaDb("norme");

}
