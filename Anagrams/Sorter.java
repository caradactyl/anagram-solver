package anagrams;

/**
 * Sort char arrays. Used by Word class.
 * 
 * @author Cara Magliozzi
 * 
 */
public class Sorter {

    /** Calls private quicksort method. */
    public static void sort(char[] chars) {
        quicksort(chars, 0, chars.length - 1);
    }

    /** Sorts char arrays lexicographically via randomized quicksort. */
    private static void quicksort(char[] chars, int start, int end) {
        if (start <= end) {
            int pivot = choosePivot(chars, start, end);
            pivot = partition(chars, start, end, pivot);
            quicksort(chars, start, pivot - 1);
            quicksort(chars, pivot + 1, end);
        }
    }

    /** Returns random pivot to ensure O(n log n) worst case runtime. */
    private static int choosePivot(char[] chars, int start, int end) {
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    /** Partitions array around a pivot index. */
    private static int partition(char[] chars, int start, int end, int pivot) {
        char pivotValue = chars[pivot];
        swap(chars, pivot, end);
        int storeIndex = start;
        for (int i = start; i < end; i++) {
            if (chars[i] <= pivotValue) {
                swap(chars, storeIndex, i);
                storeIndex++;
            }
        }
        swap(chars, storeIndex, end);
        return storeIndex;
    }

    /** Swaps char at index i with char at j. */
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
