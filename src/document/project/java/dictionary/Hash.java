package document.project.java.dictionary;

import java.util.function.Function;

/**
 * A simple hash used as default by the Dictionaries of
 * this package
 *
 * @author Hilson Alexandre Wojcikiewicz Junior
 */
public class Hash<K> implements Function <K, Integer> {

    /**
     * The hash function (simple
     * {@code hashCode} mod 10)
     * @param key an element to hash
     * @return the hash of the key
     */
    @Override
    public Integer apply(K key) {

        return Math.abs(key.hashCode() % 10);
    }

}
