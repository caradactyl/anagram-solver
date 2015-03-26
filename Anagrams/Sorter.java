package anagrams;

/**
 * Sorting functions for the Word class. Both counting sort and quicksort have
 * been implemented, but currently only counting sort is in use.
 * 
 * counting sort : O(n)
 * quicksort : O(n log n)
 * 
 * @author Cara Magliozzi
 * 
 */
public class Sorter {

    /** Calls private countingSort method. */
    public static char[] sort(char[] chars) {
        return countingSort(chars);
    }

    /** Returns a lexicographically sorted char array via counting sort. */
    private static char[] countingSort(char[] chars) {
        int[] histogram = new int[26];
        for (int i = 0; i < chars.length; i++) {
            histogram[chars[i] - 'a'] += 1;
        }
        int total = 0;
        int oldCount;
        for (int i = 0; i < 26; i++) {
            oldCount = histogram[i];
            histogram[i] = total;
            total += oldCount;
        }
        char[] output = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[histogram[chars[i] - 'a']] = chars[i];
            histogram[chars[i] - 'a'] += 1;
        }
        return output;
    }

    /** Calls private quicksort method. */
    public static void quicksort(char[] chars) {
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
