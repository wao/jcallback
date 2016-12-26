package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public interface CallbackProvider<U,V> {
    Invoker<U> apply(V data);
}
