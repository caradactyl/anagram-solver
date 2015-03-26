package anagrams;

/**
 * Anagram class containing all Words from the Dictionary that are anagrams of one another.
 * 
 * @author Cara Magliozzi
 * 
 */
public class Anagram {

    private String key;
    private Word[] words;
    private int capacity;
    private int size;

    /** Constructor. */
    public Anagram(String key) {
        this.key = key;
        capacity = 10;
        words = new Word[capacity];
        size = 0;
    }

    /** Adds Word to Anagram class. */
    public void addWord(Word word) {
        if (size >= words.length) {
            resize(2 * words.length);
        }
        words[size] = word;
        size++;
    }

    /** Returns key, a sorted version of the Words in the class. */
    public String getKey() {
        return key;
    }

    /** Doubles capacity of Word array. Called automatically when array is full. */
    public void resize(int newCapacity) {
        Word[] oldTable = words;
        words = new Word[newCapacity];
        for (int i = 0; i < oldTable.length; i++) {
            words[i] = oldTable[i];
        }
    }

    /** Returns number of Words in Anagram class. */
    public int size() {
        return size;
    }

    /** Returns String representation of Anagram class. */
    public String toString() {
        StringBuilder anagram = new StringBuilder();
        for (Word word : words) {
            if (word != null) {
                anagram.append(word.toString() + " ");
            }
        }
        return anagram.toString().trim();
    }

}
