package info.thinkmore.jcallback;

import java.util.function.*;

/**
 * Created by yangchen on 16-12-26.
 */
public class JConsumerCallback<U> extends JCallback<U> implements Invoker<U> {
    Consumer<U> mOp;
    public JConsumerCallback(Consumer op){
        mOp = op;
    }

    @Override
    public void onCall(U data){
        mOp.apply(data);
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
