package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JTeeCallback<U> extends JCallback<U> implements Invoker<U> {
    Invoker<U> mTee;

    JTeeCallback(Invoker<U> origin, Invoker<U> tee){
        mNext = origin;
        mTee = tee;
    }

    @Override
    public void onCall(U data){
        callNext(data);
        mTee.onCall(data);
    }

    @Override
    public void onError(Throwable e){
        nextError(e);
    }

    @Override
    protected Invoker<U> getInvoker(){
        return this;
    }
}
