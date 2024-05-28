/**
 * CollectionSort uses the quick sort algorithm for sorting arrays.
 * Quick sort is not the best method for finding the median as it sorts the entire array, which is less efficient when only the median is needed.
 *
 * A better approach for finding the median is the Quick select algorithm. Quick select finds the k-th smallest element with average-case complexity of O(n) and doesn't require sorting the entire array. It partitions the array around a pivot, similar to quicksort, but only recursively processes the part containing the k-th element.
 */
package Question_2;

/**
 *
 * @author xhu
 */
public class SortArray<E extends Comparable<E>> {

    private E[] array;

    public SortArray(E[] array) {
        this.array = array;
    }

    public void setArray(E[] array) {
        this.array = array;
    }

    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        E pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

