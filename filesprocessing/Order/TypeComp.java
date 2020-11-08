package filesprocessing.Order;

import java.io.File;

public class TypeComp extends Comparators {
    /***
     * Get the file Extension (type of file)
     * @param fullName
     * @return
     */
    public static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /***
     * Comparator by Type of files,
     * those with same type,
     * compared by name
     * @param f1
     * @param f2
     * @return
     */
    public int compare(File f1, File f2) {
        int difference = (getFileExtension(f1.getName()).compareTo(getFileExtension(f2.getName())));
        if (difference == 0) {
            return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
        }
        return difference;
    }

}
