package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-25.
 */
public class JCallback<U>{
    protected Invoker<U> mNext;
    String mName;

    public <V> JCallback<V> then(Func<V,U> func){
        return then(func, "");
    }

    public <V> JCallback<V> then(Func<V,U> func, String name){
        JFuncCallback<V,U> next = new JFuncCallback<V,U>(func);
        next.setName(name);
        hook(next);
        return next;
    }

    public <V> JCallback<V> thenChain(CallbackProvider<V,U> func){
        return thenChain(func, "");
    }

    public <V> JCallback<V> thenChain(CallbackProvider<V,U> func, String name){
        JProviderCallback<V,U> next = new JProviderCallback<V,U>(func);
        next.setName(name);
        hook(next);
        return next;
    }

    public <V> JCallback<V> thenChain(JCallback<V> u){
        return thenChain(u, "");
    }

    public <V> JCallback<V> thenChain(JCallback<V> u, String name){
        JChainCallback<V,U> next = new JChainCallback<>(u);
        next.setName(name);
        hook(next);
        return u;
    }

    public JCallback<U> then(Runnable func){
        return then(func, "");
    }

    public JCallback<U> then(Runnable func, String name){
        JRunnableCallback<U> next = new JRunnableCallback<U>(func);
        next.setName(name);
        hook(next);
        return next;
    }

    public JCallback<U> thenConsume(Consumer<U> func){
        return thenConsume(func, "");
    }

    public JCallback<U> thenConsume(Consumer<U> func, String name){
        JConsumerCallback<U> next = new JConsumerCallback<U>(func);
        next.setName(name);
        hook(next);
        return next;
    }

    public <V> JCallback<V> then(ConsumerWithInvoker<U,V> f){
        return then(f, "");
    }

    public <V> JCallback<V> then(ConsumerWithInvoker<U,V> f, String name){
        JTransCallback<U,V> next = new JTransCallback<U,V>(f);
        next.setName(name);
        hook(next);
        return next;
    }


    public JCallback<U> error(Func<Throwable, Throwable> f){
        return error(f, "");
    }

    public JCallback<U> error(Func<Throwable, Throwable> f, String name){
        JErrorCallback<U> next = new JErrorCallback<U>(f);
        next.setName(name);
        hook(next);
        return next;
    }

    protected void hook(Invoker<U> invoker){
       if( mNext == null ){
           mNext = invoker;
       }
       else{
           throw new RuntimeException("JCallback ["+mName+"] has already be chained, maybe you should use tee() here.");
       }
    }

    public JCallback<U> tee(){
        JTeeCallback<U> next = new JTeeCallback<U>(mNext);
        mNext = next;
        return next;
    }

    public static interface OnCreate<U>{
        void apply(Invoker<U> invoker);
    }

    public static <U> JCallback<U> create(OnCreate<U> f){
        return create(f, "");
    }

    public static <U> JCallback<U> create(OnCreate<U> f, String name){
        JCaller<U> cb = new JCaller<U>();
        cb.setName(name);
        f.apply(cb);
        return cb;
    }

    protected void callNext(Invoker<U> next, U data){
        try {
            if (next != null) {
                next.onCall(data);
            }
        }catch(Throwable e){
            nextError(next, e);
        }
    }

    protected void callNext(U data){
        callNext(mNext, data);
    }

    protected void nextError(Throwable e){
        nextError(mNext, e);
    }

    protected void nextError(Invoker<U> next, Throwable e){
        if( next != null ) {
            next.onError(e);
        }
        else{
            throw new RuntimeException(e);
        }
    }

    protected void setName(String name){
        mName = name;
    }
}
