package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JFuncCallback<U,V> extends JCallback<U> implements Invoker<V>{
       Func<U,V> mFunc;
       JFuncCallback(Func<U,V> func){
              mFunc = func;
       }

       @Override
       public void onCall(V data){
           callNext(mFunc.apply(data));
       }

       @Override
       public void onError(Throwable e){
           nextError(e);
       }

}
