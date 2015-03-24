package anagrams;

/********************************************************************************************
 * Anagram class containing all Words from the Dictionary that are anagrams of one another. 
 * 
 * @author Cara Magliozzi
 *
 ********************************************************************************************/
public class Anagram {
	
	private String key;
	private Word[] words;
	private int count, capacity;	

	/********************************************************************************************
	 * Construct Anagram classes.
	 ********************************************************************************************/
	public Anagram(String key) {
		this.key = key;
		capacity = 10;
		count = 0;
		words = new Word[capacity];
	}
	
	/********************************************************************************************
	 * Return the number of Words in the Anagram class.
	 ********************************************************************************************/
	public void addWord(Word w) {
		words[count] = w;
		count++;
		if (count > capacity/2) expandTable();
	}
	
	/********************************************************************************************
	 * Return the key, a sorted version of the Words in the class.
	 ********************************************************************************************/
	public String getKey() { return key; }
	
	
	/********************************************************************************************
	 * Return the number of Words in the Anagram class.
	 ********************************************************************************************/
	public int size() { return count; }
	
	
	/********************************************************************************************
	 * Expand the size of the Word array to double the capacity.  
	 ********************************************************************************************/
	private void expandTable() {
		Word[] oldTable = words;
		
		capacity *= 2;
		words = new Word[capacity];
		
		for (int i = 0; i < oldTable.length; i++) {
			words[i] = oldTable[i];
		}
	}
	
	/********************************************************************************************
	 * Return the String representation of an Anagram class.
	 ********************************************************************************************/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Word w : words) {
			if (w != null)
				sb.append(w.toString()+" ");
		}
		return sb.toString();
	}
	
}
