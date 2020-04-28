package dr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableAny;
import libs.coronaDb.coCollection;
import libs.coronaDb.coronaDb;
import libs.requestFormer;
import org.reactivestreams.Subscriber;

final public class FinalsVal {

       static public final SynchronousQueue<Patient> respond = new SynchronousQueue<>();

       static public final SynchronousQueue<requestFormer<Patient>> requestP = new SynchronousQueue<>();
       static public final SynchronousQueue<requestFormer<Drug>> requestD = new SynchronousQueue<>();
       static public final requestFormer<Patient> formerP=new requestFormer<>();
       static public final requestFormer<Drug> formerD=new requestFormer<>();
       static public final SynchronousQueue<requestFormer<prescriptionsHistory>> requestH = new SynchronousQueue<>();
       static public final requestFormer<prescriptionsHistory> formerH=new requestFormer<>();
       static public final coronaDb database=new coronaDb("norme");

       public static boolean isNumeric(String strNum) {
              if (strNum == null) {
                     return false;
              }
              try {
                     double d = Double.parseDouble(strNum);
              } catch (NumberFormatException nfe) {
                     return false;
              }
              return true;
       }
       public static String removeSpace(String str) {
              String result = "";
              for (int i = 0; i < str.length(); i++){
                     char c = str.charAt(i);
                     if(c!=' ') {
                            result += c;
                     }
              }
              return result;
       }

}
