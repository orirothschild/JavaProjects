package il.co.ilrd.observer;
import java.util.function.Consumer;

public class CallBack<T> {
    Subject<T> subject;
    private final Consumer<T> cons;  /*costumer*/
    private final Runnable notifyDeath;

    public CallBack(Consumer<T> cons, Runnable notifyDeath) {
        this.cons = cons;
        this.notifyDeath = notifyDeath;
    }
    
    public CallBack(Consumer<T> cons) {
        this.cons = cons;
        this.notifyDeath = ()-> {};
    }
    
    //dispacher
    void notifyDeath() {
        notifyDeath.run();
    }
    
    public void update(T arg) {
    	cons.accept(arg);
    }
    
	public void Stop(){
		subject.unregister(this);
    }
}
