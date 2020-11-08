package filesprocessing.Filtering;
import java.io.File;
import java.util.function.Predicate;
import java.util.logging.Filter;

/**
 * Double using filters stored here
 * Ive used predicate in order to filter files
 */
public class SizeFilter{

    /**
     *Bigger than predicate
     * @param num
     * @return
     */
    public static Predicate<File> biggerFilter(double num){
        return p -> p.length() > num*1024;
    }

    /**
     * Smaller than predicate
     * @param num
     * @return
     */
    public static Predicate<File> smallerFilter(double num){
        return p -> p.length() < num*1024;
    }

    /**
     * Between predicate
     * @param inf
     * @param sup
     * @return
     */
    public static Predicate<File> betweenFilter( double inf, double sup){
        return p -> p.length() >= inf*1024 && p.length()<=sup*1024;
    }
}
