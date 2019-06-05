package il.co.ilrd.observer;
import java.util.ArrayList;
import java.util.List;

import il.co.ilrd.user.User;

public class Subject<T> {
    List<CallBack<T>> observers = new ArrayList<>();
    
    public void unregister(CallBack<T> observer) {
    	observer.subject = null;
    	observers.remove(observer);
    }

    public void register(CallBack<T> observer) {
    	observer.subject = this;
        observers.add(observer);
    }
    
    public void register(User<T> user){
    	CallBack<T> callBack = user.getCallback();
    	callBack.subject = this;
        observers.add(callBack);
    }

    public void notifyAllObservers(T t) {
        observers.forEach((x)-> x.update(t));
    }

    public void stop() {
    	
    	observers.forEach(CallBack<T> :: notifyDeath);
    	observers.clear();
    }
    
    public void nameObservers() {
    	int counter = 0;
        for (CallBack<T> c : observers) {
        	System.out.println(c + ":" + ++counter);
        }
    }
}

/*for (Iterator<CallBack<T>> i = observers.iterator(); i.hasNext(); i = observers.iterator()) {
CallBack<T> c = (i.next());
unregister(c);
i.remove();
c.notifyDeath();
i = observers.iterator();
}*/
