package filesprocessing.Filtering;

import java.io.File;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Filter class, sends files to correct filter
 */
public class Filter {

    /**
     * filter names array
     */
    private static String[] FilterNames = {"greater_than", "smaller_than", "between", "writable", "executable",
            "hidden", "all", "file", "contains", "prefix", "suffix"};


    /***
     * Gets a list of files, its easier to use predicate on them,
     * use the correct filter on them with switch,
     * than send it back as array
     * @param fileList
     * @param lineOfFilter
     * @param isNot
     * @return
     */
    public static File[] Filter(List<File> fileList, String[] lineOfFilter, boolean isNot) {
        String filter = lineOfFilter[0];
        List<File> filtered = fileList;
        switch (filter) {
            case "greater_than":
                Predicate<File> biggerPre = SizeFilter.biggerFilter(Double.valueOf(lineOfFilter[1]));
                if (isNot) {
                    biggerPre = biggerPre.negate();
                }
                filtered = filterFiles(fileList, biggerPre);
                break;
            case "smaller_than":
                Predicate<File> smallerPre = SizeFilter.smallerFilter(Double.valueOf(lineOfFilter[1]));
                if (isNot) {
                    smallerPre = smallerPre.negate();
                }
                filtered = filterFiles(fileList, smallerPre);
                break;
            case "between":
                Predicate<File> betweenPre = SizeFilter.betweenFilter(Double.valueOf(lineOfFilter[1]),
                        Double.valueOf(lineOfFilter[2]));
                if (isNot) {
                    betweenPre = betweenPre.negate();
                }
                filtered = filterFiles(fileList, betweenPre);
                break;
            case "writable":
                Predicate<File> writablePre = BooleanFilter.writableFilter(lineOfFilter[1]);
                if (isNot) {
                    writablePre = writablePre.negate();
                }
                filtered = filterFiles(fileList, writablePre);
                break;
            case "file":
                Predicate<File> filePre = StringFilter.fileFilter(lineOfFilter[1]);
                if (isNot) {
                    filePre = filePre.negate();
                }
                filtered = filterFiles(fileList, filePre);
                break;
            case "contains":
                Predicate<File> containsPre = StringFilter.containsFilter(lineOfFilter[1]);
                if (isNot) {
                    containsPre = containsPre.negate();
                }
                filtered = filterFiles(fileList, containsPre);
                break;
            case "prefix":
                Predicate<File> prefixPre = StringFilter.prefixFilter(lineOfFilter[1]);
                if (isNot) {
                    prefixPre = prefixPre.negate();
                }
                filtered = filterFiles(fileList, prefixPre);
                break;
            case "suffix":
                Predicate<File> suffixPre = StringFilter.suffixFilter(lineOfFilter[1]);
                if (isNot) {
                    suffixPre = suffixPre.negate();
                }
                filtered = filterFiles(fileList, suffixPre);
                break;
            case "executable":
                Predicate<File> executablePre = BooleanFilter.executeableFilter(lineOfFilter[1]);
                if (isNot) {
                    executablePre = executablePre.negate();
                }
                filtered = filterFiles(fileList, executablePre);
                break;
            case "hidden":
                Predicate<File> hiddenPre = BooleanFilter.hiddenFilter(lineOfFilter[1]);
                if (isNot) {
                    hiddenPre = hiddenPre.negate();
                }
                filtered = filterFiles(fileList, hiddenPre);
                break;
        }
        return filtered.toArray(new File[0]);
    }

    /***
     * The filtering process done by the predicate and the hunt
     * @param files
     * @param predicate
     * @return
     */
    public static List<File> filterFiles(List<File> files, Predicate<File> predicate) {
        return files.stream().filter(predicate).collect(Collectors.<File>toList());
    }
}

