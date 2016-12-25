package info.thinkmore.jcallback;

/**
 * Created by yangchen on 16-12-26.
 */
public interface Consumer<U> {
    void apply(U data);
}
