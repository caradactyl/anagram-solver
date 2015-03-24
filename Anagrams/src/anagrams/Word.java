package anagrams;

/********************************************************************************************
 * A Word in the Dictionary.
 * 
 * @author Cara Magliozzi
 *
 ********************************************************************************************/
public class Word {
	
	private String w;

	/********************************************************************************************
	 * Construct Word objects.
	 ********************************************************************************************/
	public Word(String w) {	this.w = w; }
	
	
	/********************************************************************************************
	 * Return a sorted version of the String corresponding to the Word.
	 ********************************************************************************************/
	public String sorted() {
		char[] c = w.toCharArray();
		sort(c);
		return new String(c);
	}
	
	
	/********************************************************************************************
	 * Return a String representation of a Word.
	 ********************************************************************************************/
	public String toString() { return w; }
	
	
	/********************************************************************************************
	 * Public helper method to hide the implementation details of the sorting.
	 ********************************************************************************************/
	public static void sort(char[] c) {
		quicksort(c, 0, c.length-1);
	}
	
	
	/********************************************************************************************
	 * Sort the word lexicographically using quicksort.
	 ********************************************************************************************/
	private static void quicksort(char[] arr, int start, int end) {
		if (start <= end) {
			int pivot = choosePivot(arr, start, end);
			pivot = partition(arr, start, end, pivot);
			quicksort(arr, start, pivot-1);
			quicksort(arr, pivot+1, end);
		}
	}
	
	
	/********************************************************************************************
	 * Returns a random pivot, ensuring the worst case runtime is O(n log n).
	 ********************************************************************************************/
	private static int choosePivot(char[] arr, int start, int end) {
		return (int) (Math.random()*(end-start+1)) + start;
	}
	
	
	/********************************************************************************************
	 * Returns a random pivot, ensuring the worst case runtime is O(n log n).
	 ********************************************************************************************/
	private static int partition(char[] arr, int start, int end, int pivot) {
		char pivotValue = arr[pivot];
		swap(arr, pivot, end);
		int storeIndex = start;
		for (int i = start; i < end; i++) {
			if (arr[i] <= pivotValue) {
				swap(arr, storeIndex, i);
				storeIndex++;
			}
		}
		swap(arr, storeIndex, end);
		return storeIndex;
	}
	
	
	/********************************************************************************************
	 * Swap two characters.
	 ********************************************************************************************/
	private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
