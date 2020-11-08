package filesprocessing.Filtering;
import java.io.File;
import java.util.function.Predicate;

/**
 * Boolean using filters stored here
 * Ive used predicate in order to filter files
 */
public class BooleanFilter{

    /**
     * executable predicate
     * @param bool
     * @return
     */
    public static Predicate<File> executeableFilter( String bool){
        boolean ans;
        if (bool.equals("YES")){
            ans=true;
        }
        else ans=false;
        return p -> p.canExecute()==ans;
    }

    /**
     * writable predicate
     * @param bool
     * @return
     */
    public static Predicate<File> writableFilter(String bool){
        boolean ans;
        if (bool.equals("YES")){
            ans=true;
        }
        else ans=false;
        return p -> p.canWrite()==ans;
    }

    /**
     * Hidden predicate
     * @param bool
     * @return
     */
    public static Predicate<File> hiddenFilter(String bool){
        boolean ans;
        if (bool.equals("YES")){
            ans=true;
        }
        else ans=false;
        return p -> p.isHidden()==ans;
    }
}
