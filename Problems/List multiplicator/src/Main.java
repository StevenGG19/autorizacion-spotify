import java.util.ArrayList;
import java.util.List;

/**
 * Class to modify
 */
class ListMultiplicator {

    /**
     * Repeats original list content provided number of times
     *
     * @param list list to repeat
     * @param n    times to repeat, should be zero or greater
     */
    public static void multiply(List<?> list, int n) {
        reverseCaptured(list, n);
    }

    private static <T> void reverseCaptured(List<T> list, int n) {
        if (n <= 0) {
            list.clear();
        } else {
            List<T> tmp = new ArrayList<>(list);
            for (int i = 0; i < n - 1; i++) {
                list.addAll(tmp);
            }
        }
    }
}