package document.project.java.ds_collection.list;

import document.project.java.ds_collection.DSCollection;

import java.util.Arrays;
import java.util.Collection;

/**
 * An simple interface for basic list methods.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior.
 *
 * @param <T> The type of the element to be stored.
 */
public interface DSList<T> extends DSCollection<T> {


    void add (T element, int index);

    T remove (int index);

    void remove (T element);

    void replace (T element, int index);

    default void addFirst (T element){
        add(element, 0);
    }

    default T removeFirst (){
        return remove(0);
    }

    default void removeFirst (T element){
        remove(indexOf(element));
    }

    default void removeLast (T element){
        remove(lastIndexOf(element));
    }

    default void removeAll(T... elements){
        Arrays.stream(elements).forEach(this::remove);
    }

    default void removeAll (Collection <? extends T> elements){
        elements.forEach(this::remove);
    }

    default void removeAll (DSCollection <? extends T> elements){
        elements.forEach(this::remove);
    }

    default void addAll (int index, T... elements){
        ListValidate.index(index, size());
        for (T element : elements) {
            add(element, index++);
        }
    }

    default void addAll (int index, Collection<? extends T> elements){
        ListValidate.index(index, size());
        for (T element : elements){
            add(element, index++);
        }
    }

    default void addAll (int index, DSCollection<? extends T> elements){
        ListValidate.index(index, size());
        for (T element : elements){
            add(element, index++);
        }
    }

    @Override
    default void add(T element) {
        add(element, size());
    }

    @Override
    default T remove() {
        return remove(size() - 1);
    }

}
