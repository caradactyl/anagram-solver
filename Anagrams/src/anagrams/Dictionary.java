package anagrams;

import java.util.Iterator;
import java.util.NoSuchElementException;

/********************************************************************************************
 * Dictionary is a hash table of Entry objects consisting of (String key, Anagram value) pairs.
 * When a Word is added to the Dictionary, the hash value of that word is computed. The hash 
 * value of a Word is a lexicographically sorted version of the String. If the hash table does
 * not contain the key, an Anagram class instance is created. If the hash table contains the 
 * key, the existing Anagram class associated to that key is retrieved. The Word is then added 
 * to the Anagram class, and the pair is put in the table.
 * 
 * @author Cara Magliozzi
 *
 ********************************************************************************************/
public class Dictionary implements Iterable<String> {
	
	
	/********************************************************************************************
	 * Entry class representing (String key, Anagram value) pairs in the Dictionary.
	 ********************************************************************************************/
	private class Entry {
		String key;
		Anagram value;
	}
		
	
	private Entry[] htable;
	private int count, capacity;
	
	
	/********************************************************************************************
	 * Construct Dictionary objects.
	 ********************************************************************************************/
	public Dictionary() {		
		capacity = 35000;
		htable = new Entry[capacity];
		count = 0;
	}
	
	
	/********************************************************************************************
	 * Add a Word to the Dictionary (and to an Anagram class). 
	 ********************************************************************************************/
	public void addWord(Word w) {
		String key = w.sorted();
		Anagram value;

		if (this.containsKey(key))	value = this.get(key);
		else						value = new Anagram(key);
		
		value.addWord(w);
		this.put(key, value);
	}
	

	/********************************************************************************************
	 * Convert a key into a hash value.
	 ********************************************************************************************/
	private int hashValue(String key) {
	    int hashval = key.hashCode() % capacity;;
	    
	    // Java's modulus implementation may result in a negative number.
	    
	    return (hashval < 0) ? -hashval : hashval;
	}
	
	
	/********************************************************************************************
	 * Expand the size of the hash table to double the capacity. 
	 ********************************************************************************************/
	private void expandTable() {
		Entry[] oldTable = htable;
		
		capacity *= 2;
		htable = new Entry[capacity];
		
		for (Entry e: oldTable) {
			if (e == null) continue;
			int idx = findSpot(e.key);
			htable[idx] = e;
		}
	}
	
	
	/********************************************************************************************
	 * Find the key value, looping until an empty slot is found (indicating the key isn't there)
	 * or until a a match is found (which means we're done).
	 ********************************************************************************************/
	private int find(String key) {
		int hkey = hashValue(key);
	
		while (htable[hkey] != null && !htable[hkey].key.equals(key))
			hkey = (hkey+1) % capacity;
		
		return (htable[hkey] != null) ? hkey : -1;
	}
	
	
	/********************************************************************************************
	 * Find the first empty slot that can hold this key, looping until an empty slot is found.
	 ********************************************************************************************/
	private int findSpot(String key) {
		int hkey = hashValue(key);
	
		while (htable[hkey] != null)
			hkey = (hkey+1) % capacity;
			
		return hkey;
	}
	
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public Anagram get(String key) {
		int idx = find(key);
		
		return (idx < 0) ? null : htable[idx].value;
	}

	
	/********************************************************************************************
	 * Put an Entry, a (String, Anagram) pair, in the hash table. 
	 * Return the value previously associated to the key, if there was one, otherwise null.
	 ********************************************************************************************/
	private Anagram put(String key, Anagram value) {		
		int idx = find(key);
		Anagram oldValue;
		Entry newEntry = new Entry();
		newEntry.key = key;
		newEntry.value = value;
		
		if (idx < 0) {
			if (count > capacity/2)	expandTable();
			oldValue = null;
			idx = findSpot(key);
		}
		else oldValue = htable[idx].value;

		htable[idx] = newEntry;
		if (oldValue == null) count++;
		
		return oldValue;
	}
	
	
	/********************************************************************************************
	 * Return true if the key is in the hash table, false otherwise.
	 ********************************************************************************************/
	private boolean containsKey(String key) { return find(key) >= 0; }
	
	
	/********************************************************************************************
	 * Return the number of Entries in the Dictionary.
	 ********************************************************************************************/
	public int size() { return count; }
	
	
	/********************************************************************************************
	 * Iterator over the keys in the hash table. 
	 * The remove operation has not been implemented as it will not be needed at this time.
	 ********************************************************************************************/
	private class DIterator implements Iterator<String>{

		int next = 0;
		int numSeen = 0;
		int prev = -1;

		public boolean hasNext() { return numSeen < count; }

		public String next() {
			if (numSeen >= count) throw new NoSuchElementException( "already returned full list" );
			
			while (next < capacity) {
				if (htable[next] != null) {
					prev = next;
					next++;
					numSeen++;
					return htable[prev].key;
				}
				next++;
			}
			throw new NoSuchElementException( "no remaining items found" );
		}

		public void remove() { throw new UnsupportedOperationException(); }
	}
	
	
	/********************************************************************************************
	 * Return an instance of the Dictionary Iterator, DIterator.
	 ********************************************************************************************/
	public Iterator<String> iterator() { return new DIterator(); }
}
