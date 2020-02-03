package document.project.java.dictionary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * A hash based data structure like {@link java.util.HashMap}.
 * This object store a given key for each element. The dictionary
 * don't store duplicate keys, and every {@link Entry} stores just
 * an element for each key.
 *
 * @implNote The default {@code constructor }uses a simple and
 * non recommended hash function that receives a key and uses it
 * {@code hashCode} % 10. It's recommended to pass your own hash
 * function passing a {@link Function<>} that receive the key type
 * as a parameter and return a {@link Integer}.
 *
 * @param <K> The Key type to search in the Dictionary {@code Synonymous}.
 * @param <T> The element type that will be stored in the dictionary.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior
 */
public class Dictionary <K, T> {

    /**
     * The {@code hash function} that will
     * be applied to every key.
     */
    private Function<K, Integer> hash;

    /**
     * The simplified array hash based that
     * stores the {@link Entry} lists.
     */
    private LinkedList<Entry>[] table;

    /**
     * Constructs a empty Dictionary with
     * a default and NON RECOMMENDED hash
     * function.
     */
    public Dictionary (){
        this.hash = new Function<K, Integer>() {
            @Override
            public Integer apply(K key) {
                return Math.abs(key.hashCode() % 10);
            }
        };
    }

    /**
     * Recommended constructor.
     * Constructs a Dictionary with a
     * given hash function.
     * @param hash the hash function. It
     *             must return an {@link Integer}.
     * @param length the size of hashes array.
     *             (Recommended the maximum
     *             value possible to the hash
     *             function + 1).
     */
    @SuppressWarnings({"unchecked", })
    public Dictionary(Function<K, Integer> hash, int length){
        this.hash = hash;
        table = new LinkedList[length];
        for (int i = 0; i < length; i++){
            table[i] = new LinkedList();
        }
    }

    /**
     * Get the number of elements.
     * @return the number of stored elements.
     */
    public int size (){
        return Arrays.stream(table).mapToInt(LinkedList::size).sum();
    }

    /**
     * Verifies if the Dictionary contains
     * any element.
     * @return {@code true} if the Dictionary
     *         don't contains any element.
     */
    public boolean isEmpty (){
        return size() == 0;
    }

    /**
     * Add an element to the Dictionary
     * @param element the element to be
     *                added.
     * @param key the key to store/ get
     *            the element.
     */
    public void put (T element, K key){
        int hashCode;
        if (!containsKey(key)){
            hashCode = hash.apply(key);
            table[hashCode].add(new Entry(key, element));
        }
    }

    /**
     * get an element mapped by the key.
     * @param key the key of the stored
     *            element.
     * @return the element stored with
     * that key.
     */
    @SuppressWarnings("unchecked")
    public T get (K key){
        LinkedList list = table[hash.apply(key)];
        for (Object entry : list){
            if (((Entry) entry).key.equals(key)){
                return ((Entry) entry).element;
            }
        }
        return null;
    }

    /**
     * remove an element of the dictionary.
     * @param key the key of the element.
     */
    @SuppressWarnings("unchecked")
    public void remove (K key){
        if (containsKey(key)){
            LinkedList list = table[hash.apply(key)];
            for (Object object : list){
                if (((Entry)object).key.equals(key)){
                    list.remove(object);
                    break;
                }
            }
        }
    }

    /**
     * return the group that a given key
     * will be.
     * @param key the key to discover the group.
     * @return the group of the key. If the key
     * don't exists it return the group that it
     * would be.
     */
    public int groupOf (K key){
        return hash.apply(key);
    }

    /**
     * Verifies if a key exists in
     * the Dictionary.
     * @param key the key to be verified.
     * @return {@code true} if exists the
     *          key on the dictionary.
     */
    public boolean containsKey (K key){
        int hashCode = (int) hash.apply(key);
        return table[hashCode].stream().anyMatch(entry -> entry.key.equals(key));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (LinkedList list : table){
            for (Object obj : list){
                builder.append(obj).append(' ');
            }
        }
        return '{' + builder.toString() + '}';
    }

    /**
     * Private class to unify the entries (Key/Element).
     *
     * @author Hilson Alexandre Wojcikiewicz Junior.
     */
    private class Entry{

        /**
         * The key to access the element.
         */
        private K key;

        /**
         * the stored element.
         */
        private T element;

        Entry(K key, T element){
            this.element = element;
            this.key = key;
        }

        @Override
        public String toString() {
            return "[" +
                     key +
                    " : " + element +
                    ']';
        }
    }
}
