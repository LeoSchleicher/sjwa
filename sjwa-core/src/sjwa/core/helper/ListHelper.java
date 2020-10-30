package sjwa.core.helper;

import java.util.List;

public class ListHelper {

    public static <E> E find(List<E> source,  Matcher<E> matcher){
        for (E e : source) {
            if(matcher.match(e)) {
                return e;
            }
        }
        return null;
    }

    public static interface Matcher<T>  {
        boolean match(T e);
    }
}
