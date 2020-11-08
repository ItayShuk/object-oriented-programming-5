package filesprocessing;

import filesprocessing.Filtering.Filter;
import filesprocessing.Order.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Parsing {

    /**
     * Whether the NOT is used properly and should be considered
     */
    protected static boolean NotCase;
    /**
     * Splitted to args filter line
     */
    protected static String[] filterPref;
    /**
     * Names of double args filter
     */
    private static List<String> doubleFilterNames = Arrays.asList("greater_than", "smaller_than", "between");
    /**
     * Names of boolean args filter
     */
    private static List<String> boolFilterNames = Arrays.asList("writable", "executable", "hidden");
    /**
     * Names of String args filter
     */
    private static List<String> stringFilterNames = Arrays.asList("file", "contains", "prefix", "suffix");
    /**
     * Splitter for lines
     */
    private static String SPLITTER = "#";
    private static String typeOneMsg = "Warning in line ";
    /**
     * Default for non valid filter
     */
    private static String[] DEFAULT_FILTER = new String[]{"all"};
    /**
     * Default for non valid sort
     */
    private static String[] DEFAULT_ORDER = new String[]{"abs"};
    /**
     * Currently the number of line
     */
    private static int LINE_NUM;
    /**
     * Type1 Exception obj
     */
    protected static TypeOneEx typeOneEx = new TypeOneEx(typeOneMsg + LINE_NUM);
    /**
     * The guy who in charge of checking args
     */
    private static FilterArgsCheck checker;

    private static String REVERSE = "REVERSE";

    private static String SIZE = "size";

    private static String ALL = "all";

    private static String NOT = "NOT";

    private static String ABS = "abs";

    private static String TYPE = "type";

    /***
     *Gets a filter line, files and number of line we currently at,
     * determent which filter is it, and proceed to check the args
     * of the line
     * @param line
     * @param files
     * @param lineNum
     * @return
     *
     */
    public static File[] filterLine(String line, File[] files, int lineNum) {
        filterPref = line.split(SPLITTER);
        List<File> fileList = Arrays.asList(files);
        NotCase = false;
        LINE_NUM = lineNum;

        if (stringFilterNames.contains(filterPref[0])) {
            try {
                FilterArgsCheck.stringCheck();
            } catch (TypeOneEx ignored) {
                filterPref = defaultFilter();
            }
        } else if (doubleFilterNames.contains(filterPref[0])) {
            try {
                FilterArgsCheck.doubleCheck();
            } catch (TypeOneEx ignored) {
                filterPref = defaultFilter();
            }
        } else if (boolFilterNames.contains(filterPref[0])) {
            try {
                FilterArgsCheck.boolCheck();
            } catch (TypeOneEx ignored) {
                filterPref = defaultFilter();
            }
        } else if (!filterPref[0].equals(ALL)) {
            filterPref = defaultFilter();
        }
        return Filter.Filter(fileList, filterPref, NotCase);
    }

    /***
     * If the filter line isn't valid,
     * this function prints the error and line number,
     * after it, set the filter to be 'all', as default
     * @return
     */
    private static String[] defaultFilter() {
        String[] filterPref;
        System.err.println(typeOneMsg + LINE_NUM);
        filterPref = DEFAULT_FILTER;
        return filterPref;
    }

    /***
     * Checks if the last arg is NOT
     * @param filterPref
     * @return
     */
    protected static boolean notProblemCase(String[] filterPref) {
        return (!filterPref[filterPref.length - 1].equals(NOT));
    }


    /***
     * Gets a sort line, files and number of line we currently at,
     * determent which sort is it,
     * after it, send it to quickSort algorithm with the
     * right comparator
     * @param line
     * @param files
     * @param lineNum
     * @return
     */
    public static File[] sortLine(String line, File[] files, int lineNum) {
        String[] sortPref = line.split(SPLITTER);

        if (sortPref.length == 0 || sortPref.length > 2) {
            System.err.println(typeOneMsg + lineNum);
            sortPref = DEFAULT_ORDER;
        }

        if (sortPref[0].equals(ABS)) {
            Comparators comp = new NameComp();
            QuickSort quickSort = new QuickSort(comp);
            quickSort.quickSort(files, 0, files.length - 1);
        } else if (sortPref[0].equals(TYPE)) {
            Comparators typeComp = new TypeComp();
            QuickSort quickSort = new QuickSort(typeComp);
            quickSort.quickSort(files, 0, files.length - 1);
        } else if (sortPref[0].equals(SIZE)) {
            Comparators sizeComp = new SizeComp();
            QuickSort quickSort = new QuickSort(sizeComp);
            quickSort.quickSort(files, 0, files.length - 1);
        } else {
            System.err.println(typeOneMsg + lineNum);
            Comparators nameComp = new NameComp();
            QuickSort quickSort = new QuickSort(nameComp);
            quickSort.quickSort(files, 0, files.length - 1);
        }

        if (sortPref.length == 2) {
            if (!sortPref[1].equals(REVERSE)) {
                System.err.println(typeOneMsg + lineNum);
                Comparators comp = new NameComp();
                QuickSort quickSort = new QuickSort(comp);
                quickSort.quickSort(files, 0, files.length - 1);
            } else {
                return reverse(files);
            }
        }
        return files;
    }

    /***
     * Reverse the order of the files
     * @param files
     * @return
     */
    private static File[] reverse(File[] files) {
        for (int i = 0; i < files.length / 2; i++) {
            File temp = files[i];
            files[i] = files[files.length - i - 1];
            files[files.length - i - 1] = temp;
        }
        return files;
    }
}
