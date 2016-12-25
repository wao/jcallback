package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-25.
 */
public interface Invoker<U>{
    public void onCall(U data);
    public void onError(Throwable e);
}
