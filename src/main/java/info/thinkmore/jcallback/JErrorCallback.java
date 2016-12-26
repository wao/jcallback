package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JErrorCallback<U> extends JCallback<U> implements Invoker<U> {
    Func<Throwable,Throwable> mFunc;
    JErrorCallback(Func<Throwable,Throwable> func){
        mFunc = func;
    }

    @Override
    public void onCall(U data){
        callNext(data);
    }

    @Override
    public void onError(Throwable e){
        Throwable ret = mFunc.apply(e);
        if( e != null) {
            nextError(e);
        }
    }

    @Override
    protected Invoker<U> getInvoker(){
        return this;
    }
}
