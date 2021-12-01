package ch.rafi.weather_oracle.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ObjUtil {

    public static <T> T assertNotNull(T element){
        return assertNotNull(element, "");
    }
    public static <T> T assertNotNull(T element, String errorString){
        if(element == null){
            throw new AssertionError(errorString);
        }
        return element;
    }

    public static <T> boolean isInList(T obj, List<T> list) { return isContained(obj, list.toArray()); }

    @SafeVarargs
    public static <T> boolean isContained(T obj, T... list) {
        if(list != null) {
            for (T ele : list) {
                if(obj == ele) return true;
            }
        }
        return false;
    }

    public static <T> T assertUnique(Iterable<T> collection) {
        T res;
        try {
            Iterator<T> it = collection.iterator();
            res = it.next();
            if(it.hasNext()){
                throw new AssertionError("not Unique");
            }
        } catch (NullPointerException|NoSuchElementException es){
            return null;
        }
        return res;
    }

    public static <T> boolean equals(T a, T b){
        if(a == null){
            return b == null;
        }
        return a.equals(b);
    }

    public static <T> boolean sameElements(T[] array1, T[] array2) {
        if(array1.length != array2.length){ return false;}
        for(T element: array1){
            if(!isContained(element, array2)){ return false; }
        }
        return true;
    }

    public static <T> List<T> concatDistinct(List<T> list1, List<T> list2) {
        return concat(list1, list2).stream().distinct().collect(Collectors.toList());
    }
    public static <T> List<T> concat(List<T> list1, List<T> list2) {
        list1.addAll(list2);
        return list1;
    }

    public static <T> void addDistinct(List<T> list, T toAdd) {
        if(!list.contains(toAdd)){
            list.add(toAdd);
        }
    }

    public static String toString(Object o){
        return o != null ? o.toString() : null;
    }

    public static <T> String toString(Collection<T> collection) {
        StringBuilder sb = new StringBuilder();
        for(T t: collection){
            sb.append(t.toString()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length()-2) : null;
    }
}
