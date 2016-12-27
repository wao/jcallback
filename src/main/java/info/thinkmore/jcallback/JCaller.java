package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JCaller<U> extends JCallback<U> implements Invoker<U> {
    @Override
    public void onCall(U data){
        callNext(data);
    }

    @Override
    public void onError(Throwable e){
        nextError(e);
    }

}
