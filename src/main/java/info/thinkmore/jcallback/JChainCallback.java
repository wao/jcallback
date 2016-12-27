package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JChainCallback<V,U> extends JCallback<V> implements Invoker<U>{
    public JChainCallback(JCallback<V> callback){
    }

    @Override
    public void onCall(U data){
        //We don't call next Callback because next callback should have already genereated,
        //and suppose to be invoked by some third-party code.
        //callNext(null);
    }

    @Override
    public void onError(Throwable e){
        nextError(e);
    }

}
