package anagrams;

import java.util.Iterator;

/**
 * Interface of required methods for the Dictionary class. Created this instead of using the
 * Java-provided interface because a number of its methods were not needed at this time.
 * 
 * @author Cara Magliozzi
 * 
 */
public interface MapInterface<K, V> extends Iterable<K> {

    public boolean containsKey(Object key);

    public V get(Object key);

    boolean isEmpty();

    public V put(K key, V value);

    public int size();

    public Iterator<K> iterator();

}
