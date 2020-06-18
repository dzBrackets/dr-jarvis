package libs.coronaDb;


import org.josql.QueryParseException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class coCollection < type > extends ArrayList < type >  {
    private String name;
    private String path;
    private Class<type> className;
    private final QuerySelector<type> query;
    private final coronaDb mother;
    private final FileChannel channel;
    private FileLock lock=null;
    public coCollection(String name, String path, Class<type> className, coronaDb mother) throws IOException {
        super();
        channel=new RandomAccessFile(path, "rw").getChannel();

       // channel = FileChannel.open(Path.of(path), StandardOpenOption.APPEND);
        this.name = name;
        this.path = path;
        this.className = className;
        this.mother=mother;
        query=new QuerySelector<>(this);



    }



    public void insertMany(type[] data) throws IOException {
        unlock();

        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        String jsonString =_jsonStringFixer(coronaDb.mapper.writeValueAsString(data),true);
        new BufferedWriter(new FileWriter(path, true)).append(jsonString).append(",").close();
        lock();

        mother.updateSize(name,size());


    }
    public void insertOne(type data) throws IOException {

        if(data!=null){
            unlock();
            add(data);
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            String jsonString =  coronaDb.mapper.writeValueAsString(data);
            new BufferedWriter(new FileWriter(path, true))
                    .append(jsonString).append(",")
                    .close();
            lock();
        }
        mother.updateSize(name,size());

    }


    public void updateOne(type oldObject) {
        remove(oldObject);

        add(oldObject);
        //this.add(indexOf(getLike(oldObject)), newObject);
        reSave();
    }
    public void removeOne(type data) {
        remove(data);
        reSave();
        try {
            mother.updateSize(name,size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeBunch(type[] data) {

        removeAll(Arrays.asList(data));
        reSave();

        try {
            mother.updateSize(name,size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void reSave() {
        try {
            unlock();
            String jsonString = coronaDb.mapper.writeValueAsString(this.toArray());
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(_jsonStringFixer(jsonString,false));
            writer.close();
            if(size()>0)
                new BufferedWriter(new FileWriter(path, true)).append(",").close();
            lock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public <doc> List < type > findByObject(String getter, List<doc> list) {

        Method f;
        try {
            f = className.getMethod(getter);
        } catch (NoSuchMethodException e) {
            return null;
        }

        return this.stream()
                .filter(t-> {
                    try {
                        @SuppressWarnings("unchecked")
                        List < doc > b = (ArrayList < doc > ) f.invoke(t);
                        return b.containsAll(list);
                    } catch (IllegalAccessException | InvocationTargetException e) {

                        return false;
                    }
                }).collect(Collectors.toList());
    }

    public type getLike(type object){
        return this.stream().filter(t->t.equals(object)).findAny().orElse(null);
    }

    public List < type > findByObject(String getter, Object object) {

        Method f;
        try {

            f = className.getMethod(getter);
        } catch (NoSuchMethodException e) {
            return null;
        }

        return this.stream()
                .filter(t-> {
                    try {

                        return f.invoke(t).equals(object);
                    } catch (IllegalAccessException | InvocationTargetException e) {

                        return false;
                    }
                }).collect(Collectors.toList());
    }
    static public String _jsonStringFixer(String str,boolean both) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            if(both)
                str = str.substring(1);

        }

        return str;
    }


    public QuerySelector<type> querySelector(String selectwhat,String Clausers) throws QueryParseException {
        //SELECT * FROM className.getClass WHERE IT FUCKEDUP
        System.out.println(selectwhat+" FROM "+className.getName()+" "+Clausers);
        return query.parser(selectwhat+" FROM "+className.getName()+" "+Clausers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class < type > getClassName() {
        return className;
    }

    public void setClassName(Class < type > className) {
        this.className = className;
    }
    public void lock() throws IOException {
        lock = channel.lock();
    }
    public void unlock() throws IOException {
        if(lock!=null)
        lock.release();
    }
}