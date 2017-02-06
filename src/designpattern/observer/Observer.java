package designpattern.observer;

/**
 * Created by billjyc on 2016/11/6.
 */
public interface Observer {
    public void update(); //method to update the observer, used by subject

    public void setSubject(Subject sub);
}
