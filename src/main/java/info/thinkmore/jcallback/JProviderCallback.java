package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JProviderCallback<V,U> extends JCallback<V> implements Invoker<U>{
    CallbackProvider<V,U> mProvider;

    JProviderCallback(CallbackProvider<V,U> provider){
        mProvider = provider;
    }

    @Override
    public void onCall(U data){
        JCallback<V> cb = mProvider.apply(data);
        cb.mNext = mNext;
    }

    @Override
    public void onError(Throwable e) {
        nextError(e);
    }
}
