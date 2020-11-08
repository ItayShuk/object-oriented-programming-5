package filesprocessing.Order;

import java.io.File;

/**
 * QuickSort Class, for quick sorting
 */
public class QuickSort {

    Comparators comp;

    /***
     * Constructor for the certain comparator we need
     * @param comp
     */
    public QuickSort(Comparators comp) {
        this.comp = comp;
    }

    /***
     * Sorts the files based on quick sort
     * @param files
     * @param inf
     * @param sup
     */
    public void quickSort(File[] files, int inf, int sup) {
        if (inf < sup) {
            int k = partition(files, inf, sup);

            quickSort(files, inf, k - 1);
            quickSort(files, k + 1, sup);
        }
    }

    /***
     * Getting the partition index,
     * by the index determent the array cut and handle
     * @param files
     * @param inf
     * @param sup
     * @return
     */
    public int partition(File[] files, int inf, int sup) {
        File turn = files[sup];
        int i = inf - 1;
        for (int j = inf; j < sup; j++) {
            if (comp.compare(files[j], turn) <= 0) {
                i++;
                swapping(files, i, j);
            }
        }
        swapping(files, i + 1, sup);
        return i + 1;
    }

    /***
     * Swaps two file giving two indexes
     * @param files
     * @param i
     * @param j
     */
    private void swapping(File[] files, int i, int j) {
        File swapTemp = files[i];
        files[i] = files[j];
        files[j] = swapTemp;
    }
}
