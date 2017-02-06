package designpattern.observer;

/**
 * Created by billjyc on 2016/11/6.
 */
public interface Subject {
    public void register(Observer obj);
    public void unregister(Observer obj);

    public void notifyObservers();
    public Object getUpdate(Observer obj);
}
