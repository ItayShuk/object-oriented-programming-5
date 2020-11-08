package filesprocessing;

/***
 * Messy class, checks whether the args inserted to the filter line
 * are valid.
 * First checks if there is a NOT potential and than if its valid,
 * after it checks the other args
 */
public class FilterArgsCheck extends Parsing {

    /***
     * Checking string type filter args
     * @throws TypeOneEx
     */
    protected static void stringCheck() throws TypeOneEx {
        if (filterPref.length == 3) {
            if (notProblemCase(filterPref)) {
                throw typeOneEx;
            } else {
                NotCase = true;
            }
        }
    }

    /***
     * Checking boolean type filter args
     * @throws TypeOneEx
     */
    protected static void boolCheck() throws TypeOneEx {
        if (filterPref.length == 3) {
            if (notProblemCase(filterPref)) {
                throw typeOneEx;
            } else {
                NotCase = true;
            }
        }
        if (filterPref.length == 1 ||
                (filterPref.length != 1 &&
                        !filterPref[1].equals("YES") && !filterPref[1].equals("NO"))) {
            throw typeOneEx;
        }
    }

    /***
     * Checking double type filter args
     * Separated for 'between' and the others,
     * between is different because the number of args inserted
     * @throws TypeOneEx
     */
    protected static void doubleCheck() throws TypeOneEx {
        if (filterPref[0].equals("between")) {
            betweenCheck();
        } else {
            double var1 = Double.valueOf(filterPref[1]);
            if (filterPref.length >= 3) {
                if (filterPref.length > 3) {
                    throw typeOneEx;
                } else if (notProblemCase(filterPref)) {
                    throw typeOneEx;
                } else {
                    NotCase = true;
                }
            }
            if (var1 < 0) {
                throw typeOneEx;
            }
        }
    }

    /***
     * Between check
     * @throws TypeOneEx
     */
    private static void betweenCheck() throws TypeOneEx {
        double var1 = Double.valueOf(filterPref[1]);
        double var2 = Double.valueOf(filterPref[2]);
        if (filterPref.length == 4) {
            if (notProblemCase(filterPref)) {
                throw typeOneEx;
            } else {
                NotCase = true;
            }
        }
        if (var1 < 0 || var2 < 0 || var1 > var2) {
            throw typeOneEx;
        }
    }
}
