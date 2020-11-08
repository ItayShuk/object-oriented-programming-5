package filesprocessing;

import java.io.File;

/***
 * In charge of printing names
 */
public class Editor {

    /***
     * Gets filtered sorted fresh files, and printing their names
     * @param fileList
     */
    public void printNames(File[] fileList) {
        for (File fileFromList : fileList) {
            if (!fileFromList.isDirectory()) {
                System.out.println(fileFromList.getName());
            }
        }
    }
}
