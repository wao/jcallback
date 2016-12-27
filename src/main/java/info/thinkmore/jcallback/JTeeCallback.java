package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JTeeCallback<U> extends JCallback<U> implements Invoker<U> {
    Invoker<U> mTee;

    JTeeCallback(Invoker<U> origin){
        mNext = null;
        mTee = origin;
    }

    @Override
    public void onCall(U data){
        callNext(mTee, data);
        callNext(data);
    }

    @Override
    public void onError(Throwable e){
        nextError(mTee, e);
    }
}
