package fall2021.hw5;

import static fall2021.hw5.SortUtils.less;

/**
 * This class is taken from
 * https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/sorts/SortAlgorithm.java
 *
 *
 */
class InsertionSort implements SortAlgorithm {

    /**
     * Generic insertion sort algorithm in increasing order.
     *
     * @param array the array to be sorted.
     * @param <T> the class of array.
     * @return sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T insertValue = array[i];
            int j;
            for (j = i - 1; j >= 0 && less(insertValue, array[j]); j--) {
                array[j + 1] = array[j];
            }
            if (j != i - 1) {
                array[j + 1] = insertValue;
            }
        }
        return array;
    }
}
