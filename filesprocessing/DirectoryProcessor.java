package filesprocessing;

import java.io.*;


public class DirectoryProcessor {

    /**
     * command file
     */
    private File myFile;

    /**
     * list of file in dir
     */
    private File[] fileList;

    /**
     * files after filter and sort
     */
    private File[] filteredSortedFiles;

    /**
     * files after filter
     */
    private File[] filteredFiles;

    /**
     * reader of file
     */
    private BufferedReader reader;

    /**
     * line says which filter is this
     */
    private String filterLine;

    /**
     * command says its sub filter
     */
    private String filterSectionLine;

    /**
     * command says its sub order
     */
    private String orderSectionLine;

    /**
     * line says which order is this
     */
    private String orderLine;

    /**
     * check line after to know how the section is built
     */
    private String nextLine;

    /**
     * number of lines
     */
    private int lineNum;

    /**
     * the editor, prints all names
     */
    private Editor edit;

    /**
     * Filter section
     */
    private String FILTER = "FILTER";

    /**
     * Order section
     */
    private String ORDER = "ORDER";

    /**
     * Default filter
     */
    private String ABS = "abs";

    private String ErrorFilterTypeTwo = "ERROR: FILTER sub-section missing";

    private String ErrorFilterLineTypeTwo = "ERROR: FILTER line missing";

    private String ErrorOrderTypeTwo = "ERROR: ORDER sub-section missing";

    /***
     * Checking for Type2 Errors,
     * after check start processing command file
     * @param args
     * @throws TypeTwoEx
     * @throws IOException
     */
    private DirectoryProcessor(String[] args) throws TypeTwoEx, IOException {
        myFile = new File(args[1]);
        fileList = new File(args[0]).listFiles();
        edit = new Editor();
        creatingReader();
        typeTwoCheck(reader);
        creatingReader();
        processing(reader);
    }

    /***
     * In charge of catching Exceptions, and running
     * directory processor
     * @param args
     */
    public static void main(String[] args) {
        try {
            new DirectoryProcessor(args);
        } catch (TypeTwoEx ex) {
            System.err.print(ex.getMessage());
        } catch (IOException ignored) {
        }
    }

    /***
     * Creator of reader
     * @throws IOException
     */
    private void creatingReader() throws IOException {
        FileReader fileRead = new FileReader(myFile);
        reader = new BufferedReader(fileRead);
    }

    /***
     * Getting clean from Type2 command file,
     * filtering the files and sorting them,
     * after each printing their names through the editor
     * @param reader
     * @throws IOException
     */
    private void processing(BufferedReader reader) throws IOException {
        filterSectionLine = reader.readLine();
        filterLine = reader.readLine();
        orderSectionLine = reader.readLine();
        orderLine = reader.readLine();
        nextLine = reader.readLine();
        while (filterSectionLine != null) {
            lineNum += 2;
            filteredFiles = Parsing.filterLine(filterLine, fileList.clone(), lineNum);
            if (validOrderLine()) {
                lineNum += 2;
                filteredSortedFiles = Parsing.sortLine(orderLine, filteredFiles, lineNum);
                withOrderLineSectorSwitch();
            } else {
                lineNum += 1;
                filteredSortedFiles = Parsing.sortLine(ABS, filteredFiles, lineNum);
                noOrderLineSectorSwitch();
            }
            edit.printNames(filteredSortedFiles);
        }
        reader.close();
    }

    /***
     * Checking for Type2 Errors, if so
     * throw them back until reaches the main
     * @param reader
     * @throws IOException
     * @throws TypeTwoEx
     */
    private void typeTwoCheck(BufferedReader reader) throws IOException, TypeTwoEx {
        filterSectionLine = reader.readLine();
        filterLine = reader.readLine();
        orderSectionLine = reader.readLine();
        orderLine = reader.readLine();
        nextLine = reader.readLine();
        while (filterSectionLine != null) {
            if (!filterSectionLine.equals(FILTER)) {
                throw new TypeTwoEx(ErrorFilterTypeTwo);
            }
            if (filterLine == null) {
                throw new TypeTwoEx(ErrorFilterLineTypeTwo);
            }
            if (orderSectionLine == null || !orderSectionLine.equals(ORDER)) {
                throw new TypeTwoEx(ErrorOrderTypeTwo);
            }
            if (validOrderLine()) {
                withOrderLineSectorSwitch();
            } else {
                noOrderLineSectorSwitch();
            }
        }
        reader.close();
    }

    /***
     * Mechanism behind section reading,
     * in a case order line is exist,
     * making sure the filterSectionLine is 'next line' arg.
     * @throws IOException
     */
    private void withOrderLineSectorSwitch() throws IOException {
        filterSectionLine = nextLine;
        filterLine = reader.readLine();
        orderSectionLine = reader.readLine();
        orderLine = reader.readLine();
        nextLine = reader.readLine();
    }

    /***
     * Mechanism behind section reading,
     * in a case order line isn't exist,
     * making sure the filterSectionLine is 'order line' arg.
     * and filterLine is 'next line' arg
     * @throws IOException
     */
    private void noOrderLineSectorSwitch() throws IOException {
        filterSectionLine = orderLine;
        filterLine = nextLine;
        orderSectionLine = reader.readLine();
        orderLine = reader.readLine();
        nextLine = reader.readLine();
    }


    /***
     * Check whether the order line is there or
     * is it a FILTER new section line
     * @return
     */
    private boolean validOrderLine() {
        return (orderLine != null && nextLine != null &&
                (orderLine.equals(FILTER) & nextLine.equals(FILTER))) ||
                (orderLine != null && !orderLine.equals(FILTER));
    }
}

