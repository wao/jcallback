package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public interface Callback<U> {
    Invoker<U> getInvoker();
    JCallback<U> getCallback();
}

