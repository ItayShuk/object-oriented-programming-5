package filesprocessing.Filtering;

import java.io.File;
import java.util.function.Predicate;

/**
 * String using filters stored here
 * Ive used predicate in order to filter files
 */
public class StringFilter {

    /**
     * Prefix predicate
     *
     * @param stringForCheck
     * @return
     */
    public static Predicate<File> prefixFilter(String stringForCheck) {
        return p -> p.getName().startsWith(stringForCheck);
    }

    /**
     * Suffix predicate
     *
     * @param stringForCheck
     * @return
     */
    public static Predicate<File> suffixFilter(String stringForCheck) {
        return p -> p.getName().endsWith(stringForCheck);
    }

    /**
     * File name predicate
     *
     * @param stringForCheck
     * @return
     */
    public static Predicate<File> fileFilter(String stringForCheck) {
        return p -> p.getName().equals(stringForCheck);
    }

    /**
     * Contains predicate
     *
     * @param stringForCheck
     * @return
     */
    public static Predicate<File> containsFilter(String stringForCheck) {
        return p -> p.getName().contains(stringForCheck);
    }

}
