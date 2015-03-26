package anagrams;

/**
 * Word in the Dictionary.
 * 
 * @author Cara Magliozzi
 * 
 */
public class Word {

    private String word;

    /** Constructs Word. */
    public Word(String word) {
        this.word = word;
    }

    /** Returns sorted version of String corresponding to Word. */
    public String sorted() {
        char[] wordChars = word.toCharArray();
        Sorter.sort(wordChars);
        return new String(wordChars);
    }

    /** Returns String associated to Word. */
    public String toString() {
        return word;
    }

}
