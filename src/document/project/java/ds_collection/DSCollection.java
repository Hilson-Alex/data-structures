package document.project.java.ds_collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * An interface with basic collections methods.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior.
 *
 * @param <T> The type of element to be stored.
 */
public interface DSCollection <T> extends Iterable<T> {

    /**
     * Get the number of elements {@code size} in the collection.
     * @return the size of the collection
     */
    int size();

    /**
     * Turn the collection into a empty collection.
     */
    void clean ();

    /**
     * Add a element into the collection.
     * @implNote In different Collections this
     * method works in different ways.
     * @param element the element to be added.
     */
    void add (T element);

    /**
     * Verifies if an element exists in the collection.
     * @param element the element to be verified.
     * @return {@code true} if the collection contains the element.
     */
    boolean contains (T element);

    /**
     * Verifies the first index of a element.
     * @param element the element to be verified.
     * @return the {@index} of the element or
     * -1 if the collection don't contains
     * the element
     */
    int indexOf (T element);

    /**
     * Verifies the last index of a element.
     * @param element the element to be verified.
     * @return the {@index} of the element or
     * -1 if the collection don't contains
     * the element
     */
    int lastIndexOf (T element);

    /**
     * Remove a element in the collection.
     * @implNote In different collections
     * this method work in different ways.
     * @return the removed element.
     */
    T remove ();

    /**
     * Get a element by its {@index}.
     * @param index the {@index} of the element.
     * @return the element in this {@index}.
     */
    T get (int index);

    /**
     * Turn the collection into an {@code array} of Objects.
     * @return an array with the elements in this
     * collection.
     */
    Object[] toArray ();

    /**
     * Turn the collection into a java {@link List}
     * @return a {@link List} with the elements of the
     * collection.
     */
    List<T> toList ();

    /**
     * Verifies if all elements exists in the collection.
     * @param elements the elements to be verified.
     * @return {@code true} if all elements exists or
     * {@code false} one or more elements don't exist.
     */
    default boolean containsAll (T... elements){
        return Arrays.stream(elements).allMatch(this::contains);
    }

    /**
     * Verifies if all elements exists in the collection.
     * @param elements a {@link Collection} with the elements
     * to be verified.
     * @return {@code true} if all elements exists.
     * {@code false} if one or more elements don't exist.
     */
    default boolean containsAll (Collection<? extends T> elements){
        return elements.stream().allMatch(this::contains);
    }

    /**
     * Verifies if all elements exists in the collection.
     * @param elements a {@link DSCollection} with the elements
     * to be verified.
     * @return {@code true} if all elements exists.
     * {@code false} if one or more elements don't exist.
     */
    default boolean containsAll (DSCollection<? extends T> elements){
        return elements.stream().allMatch(this::contains);
    }

    /**
     * Add all passed elements.
     * @param elements the elements to be passed.
     */
    default void addAll (T... elements){
        Arrays.stream(elements).forEach(this::add);
    }

    /**
     * Add all passed elements.
     * @param elements a {@link Collection} of elements.
     */
    default void addAll (Collection<? extends T> elements){
        elements.stream().forEach(this::add);
    }

    /**
     * Add all passed elements.
     * @param elements a {@link DSCollection} of elements.
     */
    default void addAll (DSCollection<? extends  T> elements){
        elements.stream().forEach(this::add);
    }

    /**
     *  Returns a sequential {@code Stream} with
     *  this collection as its source.
     */
    default Stream<T> stream () {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Verifies if the {@link DSCollection} have no elements.
     * @return {@code true} if the collection is empty or
     * {@code false} if the collection contains any element.
     */
    default boolean isEmpty (){
        return size() == 0;
    }


    //Iterable
    default Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = -1;
            @Override
            public boolean hasNext() { return count < size() -1; }
            @Override
            public T next() { return get(++count); }
        };
    }

}
