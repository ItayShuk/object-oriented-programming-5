package filesprocessing.Order;

import java.io.File;
import java.util.Comparator;

public class NameComp extends Comparators {
    /***
     * Comparator by name of files
     * @param f1
     * @param f2
     * @return
     */
    public int compare(File f1, File f2) {
        return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
    }
}
