package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public interface Func<RetType, ParamType>{
    RetType apply(ParamType param);
}
