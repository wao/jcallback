package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JChainCallback<V,U> extends JCallback<V> implements Invoker<U>{
    public JChainCallback(JCallback<V> callback){
        mNext = callback.getInvoker();
    }

    @Override
    public void onCall(U data){
        //callNext(null);
    }

    @Override
    public void onError(Throwable e){
        nextError(e);
    }

    @Override
    protected Invoker<V> getInvoker(){
        return null;
    }
}
