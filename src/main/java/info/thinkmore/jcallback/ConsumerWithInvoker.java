package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public interface ConsumerWithInvoker<DataType,InvokeDataType> {
    void apply(DataType data, Invoker<InvokeDataType> invoker);
}
