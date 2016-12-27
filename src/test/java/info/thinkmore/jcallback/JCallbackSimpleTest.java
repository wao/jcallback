package info.thinkmore.jcallback;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by yangchen on 16-12-25.
 */
@RunWith(Enclosed.class)
public class JCallbackSimpleTest {
    public static class SimpleCases {
        public static class CallbackInvoke{
            public interface OnCallback{
                void onRun();
            }

            OnCallback mCallback;

            public void setCallback(OnCallback cb){
               mCallback = cb;
            }

            void invoke(){
                if( mCallback != null){
                    mCallback.onRun();
                }
            }
        }

        @Test
        public void simple(){
            CallbackInvoke i = new CallbackInvoke();
            CallbackInvoke j = new CallbackInvoke();
            CallbackInvoke k = new CallbackInvoke();

            JCallback<Integer> source = JCallback.<Integer>create(c->i.setCallback(()->c.onCall(1)));

                    source.thenConsume((a)->System.out.println("Value " + a))
                    .then(()->System.out.println("Hello world2!"))
                    .then(()->System.out.println("Second!"))
                    .then(()->System.out.println("three!"))
                    .thenConsume(a->System.out.println("Value " + a))
                    .then((a,c)->{
                        Integer b = a + 1;
                        j.setCallback(()->c.onCall("Second "+b));
                     })
                    .thenConsume(a->System.out.println(a));

                    source.tee()
                        .thenChain(a->JCallback.<String>create(c->k.setCallback(()->c.onCall("hello"))))
                        .thenConsume(a->System.out.println("Should before second " + a));



            i.invoke();
            k.invoke();
            j.invoke();

            Func<Integer,Integer> a = b->1+b;
            System.out.println(a.apply(1));
        }
    }
}