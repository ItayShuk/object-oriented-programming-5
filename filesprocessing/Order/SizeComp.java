package filesprocessing.Order;

import java.io.File;

public class SizeComp extends Comparators {
    /***
     * Comparator by size of files,
     * those with same size
     * compared by name
     * @param f1
     * @param f2
     * @return
     */
    public int compare(File f1, File f2) {
        double difference = (f1.length() - f2.length());
        if (difference == 0) {
            return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
        }
        if (difference > 0) {
            return 1;
        }
        return -1;
    }
}
