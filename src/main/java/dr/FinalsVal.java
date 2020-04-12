package dr;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import DataClass.Drug;
import DataClass.Patient;
import libs.coronaDb.coCollection;
import libs.coronaDb.coronaDb;
import libs.requestFormer;
final public class FinalsVal {
       static public final SynchronousQueue<coCollection<Drug>> respondD = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Drug>> requestD = new SynchronousQueue<>();


       static public final SynchronousQueue<ArrayList<Patient>> respondP = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Patient>> requestP = new SynchronousQueue<>();
       static public final coronaDb database=new coronaDb("norme");

}
