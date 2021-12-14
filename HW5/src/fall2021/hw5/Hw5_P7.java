package fall2021.hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Credit to the algorithm writer - Uses several algorithms burrowed from
 *  https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/sorts
 *  Thanks @Podshivalov Nikita for providing sorting algorithms
 */
public class Hw5_P7 {

    public static void main(String [] args){

        for(int i = 10000; i <= 100000; i += 10000){

            List<Integer> randomNumbers = Arrays.asList(generateRandomNumbers(i, 1000000));

            long startTime, endTime, elapsedTime;
            SortAlgorithm algorithm;

            System.out.println("Running insertion sort on " + i + " numbers");

            // Measuring insertion sort run
            startTime = System.currentTimeMillis();
            algorithm = new InsertionSort();
            algorithm.sort(randomNumbers);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Running insertion sort on " + i + " numbers took " + elapsedTime);

            System.out.println("Running merge sort on " + i + " numbers");

            // Measuring merge sort run
            startTime = System.currentTimeMillis();
            algorithm = new MergeSort();
            algorithm.sort(randomNumbers);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Running merge sort on " + i + " numbers took " + elapsedTime);

            System.out.println("Running quick sort on " + i + " numbers");

            // Measuring quick sort run
            startTime = System.currentTimeMillis();
            algorithm = new QuickSort();
            algorithm.sort(randomNumbers);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Running quick sort on " + i + " numbers took " + elapsedTime);

            System.out.println("Running heap sort on " + i + " numbers");

            // Measuring heap sort run
            startTime = System.currentTimeMillis();
            algorithm = new HeapSort();
            algorithm.sort(randomNumbers);
            endTime = System.currentTimeMillis();
            elapsedTime = endTime - startTime;
            System.out.println("Running heap sort on " + i + " numbers took " + elapsedTime);
        }
    }

    /**
     * Generate 'howMany' integers upto range
     * @param howMany
     * @param range
     * @return
     */
    private static Integer [] generateRandomNumbers(int howMany, int range){

        Integer [] insertKeys = new Integer[howMany];

        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < howMany; i++){
            insertKeys[i] = random.nextInt(range) + 1;
        }

        return insertKeys;
    }
}