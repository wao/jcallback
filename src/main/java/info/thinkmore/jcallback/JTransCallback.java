package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public class JTransCallback<DataType,InvokeDataType> extends JCallback<InvokeDataType> implements Invoker<DataType> {
    ConsumerWithInvoker<DataType,InvokeDataType> mFunc;

    Invoker<InvokeDataType> mInvoker = new Invoker<InvokeDataType>(){
        @Override
        public void onCall(InvokeDataType data){
            callNext(data);
        }

        @Override
        public void onError(Throwable e) {
            nextError(e);
        }
    };

    public JTransCallback(ConsumerWithInvoker<DataType,InvokeDataType> func ){
        mFunc = func;
    }


    @Override
    public void onCall(DataType data){
        mFunc.apply(data, mInvoker);
    }

    @Override
    public void onError(Throwable e) {
        nextError(e);
    }
}
