package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JRunnableCallback<U> extends JCallback<U> implements Invoker<U> {
    Runnable mOp;
    public JRunnableCallback(Runnable op){
        mOp = op;
    }

    @Override
    public void onCall(U data){
        mOp.run();
        callNext(data);
    }

    @Override
    public void onError(Throwable e) {
        nextError(e);
    }

    @Override
    protected Invoker<U> getInvoker(){
        return this;
    }
}
