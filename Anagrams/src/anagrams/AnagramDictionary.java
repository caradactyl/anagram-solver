package anagrams;

/**
 * Specialized Dictionary for storing (String, Anagram) entries. When a Word is added to the
 * AnagramDictionary, the hash value of that word is computed. The hash value of a Word is a
 * lexicographically sorted version of the String. If the hash table does not contain the key, an
 * Anagram class instance is created. If the hash table contains the key, the existing Anagram class
 * associated to that key is retrieved. The Word is then added to the Anagram class, and the pair is
 * put in the table.
 * 
 * @author Cara Magliozzi
 * 
 */
public class AnagramDictionary extends Dictionary<String, Anagram> {

    /** Constructs map with specified capacity and load factor. */
    public AnagramDictionary(int capacity, float loadFactor) {
        super(capacity, loadFactor);
    }

    /** Constructs map with specified capacity and default load factor. */
    public AnagramDictionary(int capacity) {
        super(capacity);
    }

    /** Constructs map with capacity 95000 and a default load factor. */
    public AnagramDictionary() {
        super(95000);
    }

    /** Adds a word to the dictionary (and to appropriate Anagram class). */
    public void addWord(Word word) {
        String key = word.sorted();
        Anagram value;
        if (this.containsKey(key)) {
            value = this.get(key);
        } else {
            value = new Anagram(key);
        }
        value.addWord(word);
        this.put(key, value);
    }

}
