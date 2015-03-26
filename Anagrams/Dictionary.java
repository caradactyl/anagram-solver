package anagrams;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Hash table based implementation of the MapInterface interface. Hash collisions resolved by open
 * addressing with linear probing.
 * 
 * @author Cara Magliozzi
 * 
 */
public class Dictionary<K, V> implements MapInterface<K, V>, Iterable<K> {

    /** Creates a new entry. */
    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
    /** Creates an iterator over the keys. */
    private class KeyIterator implements Iterator<K> {

        int next = 0;
        int seen = 0;
        int previous = -1;

        public boolean hasNext() {
            return seen < size;
        }

        public K next() {
            if (seen >= size) {
                throw new NoSuchElementException("already returned full list");
            }
            while (next < table.length) {
                if (table[next] != null) {
                    previous = next;
                    next++;
                    seen++;
                    return table[previous].key;
                }
                next++;
            }
            throw new NoSuchElementException("no remaining items found");
        }

        public void remove() {
            throw new UnsupportedOperationException("removal is not supported");
        }
    }

    static final int DEFAULT_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1 << 30;

    private Entry<K, V>[] table;    // resized as necessary
    private int size;               // number of key-value mappings
    private int threshold;          // next size value to resize capacity
    private final float loadFactor; // load factor for table

    /** Constructs map with default capacity and load factor. */
    public Dictionary() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
        table = new Entry[DEFAULT_CAPACITY];
    }

    /** Constructs map with specified capacity and default load factor. */
    public Dictionary(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /** Constructs map with specified capacity and load factor. */
    public Dictionary(int capacity, float loadFactor) {
        if (capacity > MAXIMUM_CAPACITY) {
            capacity = MAXIMUM_CAPACITY;
        }
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
        table = new Entry[capacity];
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(Object key) {
        return indexFor(key) >= 0;
    }

    /** Returns value to which specified key is mapped, or null if no mapping. */
    public V get(Object key) {
        int index = indexFor(key);
        return (index < 0) ? null : table[index].value;
    }

    /** Returns true if map contains no key-value mappings. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns a KeyIterator. */
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    /**
     * Associates specified value with specified key in map. If map previously contained a mapping
     * for the key, old value is replaced.
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("null keys are not permitted");
        }
        int index = indexFor(key);
        V oldValue;
        Entry<K, V> newEntry = new Entry<K, V>(key, value);
        if (index < 0) {
            if (size >= threshold) {
                resize(2 * table.length);
            }
            oldValue = null;
            index = findEmptySlot(key);
        } else {
            oldValue = table[index].value;
        }
        table[index] = newEntry;
        if (oldValue == null) {
            size++;
        }
        return oldValue;
    }

    /** Returns number of key-value mappings in map. */
    public int size() {
        return size;
    }

    /** Finds first empty slot that can hold key and returns it. */
    private int findEmptySlot(K key) {
        int index = hash(key);
        while (table[index] != null) {
            index = (index + 1) % table.length;
        }
        return index;
    }

    /** Maps a key to a table index. Returns the index. */
    private int hash(Object key) {
        int index = key.hashCode() % table.length;
        // Java's modulus implementation may result in negative number
        return (index < 0) ? -index : index;
    }

    /** Finds index for key, looping until an empty slot or match is found. */
    private int indexFor(Object key) {
        int index = hash(key);
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % table.length;
        }
        return (table[index] != null) ? index : -1;
    }

    /**
     * Rehashes contents of map into new array with larger capacity. Called automatically when
     * number of keys reaches threshold.
     */
    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        if (oldTable.length == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        table = new Entry[newCapacity];
        threshold = (int) (newCapacity * loadFactor);
        transferFrom(oldTable);

    }

    /** Transfers all the entries from oldTable to current table. */
    private void transferFrom(Entry<K, V>[] oldTable) {
        for (Entry<K, V> entry : oldTable) {
            if (entry == null) {
                continue;
            }
            int index = findEmptySlot(entry.key);
            table[index] = entry;
        }
    }

}
