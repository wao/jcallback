package info.thinkmore.jcallback;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.Callable;

/**
 * Created by yangchen on 16-12-25.
 */
public class JCallback<U>{
    protected Invoker<U> mNext;

    public <V> JCallback<V> then(Func<V,U> func){
        JFuncCallback<V,U> next = new JFuncCallback<V,U>(func);
        mNext = next;
        return next;
    }

    public JCallback<U> then(Runnable func){
        JRunnableCallback<U> next = new JRunnableCallback<U>(func);
        mNext = next;
        return next;
    }

    public JCallback<U> then1(Consumer<U> func){
        JConsumerCallback<U> next = new JConsumerCallback<U>(func);
        mNext = next;
        return next;
    }

    public <V> JCallback<V> then(ConsumerWithInvoker<U,V> f){
        JTransCallback<U,V> next = new JTransCallback<U,V>(f);
        mNext = next;
        return next;
    }

    public JCallback<U> error(Func<Throwable, Throwable> f){
        JErrorCallback<U> next = new JErrorCallback<U>(f);
        mNext = next;
        return next;
    }

    public static interface OnCreate<U>{
        void apply(Invoker<U> invoker);
    }

    public static <U> JCallback<U> create(OnCreate<U> f){
        JCaller<U> cb = new JCaller<U>();
        f.apply(cb);
        return cb;
    }

    protected void callNext(U data){
        try {
            if (mNext != null) {
                mNext.onCall(data);
            }
        }catch(Throwable e){
            nextError(e);
        }
    }

    protected void nextError(Throwable e){
        if( mNext != null ) {
            mNext.onError(e);
        }
        else{
            throw new RuntimeException(e);
        }
    }
}
