package fall2021.hw3;

import java.util.*;

public class Hw4_p6 {

    private static int NUM_OF_RANDOMS = 100000;
    private static int MAX_INSERT_KEY_RANGE = 1000000;
    private static int MAX_SEARCH_KEY_RANGE = 2000000;

    /**
     * Tracks time for searching and inserting of different data structures
     */
    public static void timeTracker() {

        Map<Integer, Integer> myMap = new HashMap<>();
        List<Integer> myArrayList = new ArrayList<>();
        List<Integer> myLinkedList = new LinkedList<>();

        // Reusing generator
        int [] insertKeys = generateRandomNumbers(NUM_OF_RANDOMS, MAX_INSERT_KEY_RANGE);

        System.out.println("Number of keys = " + insertKeys.length);

        long startTime, endTime, elapsedTime;

        // Print out output using helper
        startTime = System.currentTimeMillis();
        insertToMap(insertKeys, myMap);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("HashMap average total insert time = " + elapsedTime);

        startTime = System.currentTimeMillis();
        insertToList(insertKeys, myArrayList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("ArrayList average total insert time = " + elapsedTime);

        startTime = System.currentTimeMillis();
        insertToList(insertKeys, myLinkedList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("LinkedList average total insert time = " + elapsedTime);

        int [] searchKeys = generateRandomNumbers(NUM_OF_RANDOMS, MAX_SEARCH_KEY_RANGE);

        startTime = System.currentTimeMillis();
        searchMap(searchKeys, myMap);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("HashMap average total search time = " + elapsedTime);

        startTime = System.currentTimeMillis();
        searchList(searchKeys, myArrayList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("ArrayList average total search time = " + elapsedTime);

        startTime = System.currentTimeMillis();
        searchList(searchKeys, myLinkedList);
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.println("LinkedList average total search time = " + elapsedTime);
    }

    /**
     * Helper to polymorphically search both list types
     * @param searchKeys
     * @param searchList
     * @return
     */
    private static long searchList(int[] searchKeys, List<Integer> searchList) {
        long matches = 0L;
        for(int key: searchKeys){
            if(searchList.contains(key)){
                matches++;
            }
        }
        return matches;
    }

    /**
     * Helper to search a map
     * @param searchKeys
     * @param searchMap
     * @return
     */
    private static long searchMap(int[] searchKeys, Map<Integer, Integer> searchMap) {
        long matches = 0L;
        for(int key: searchKeys){
            if(searchMap.containsKey(key)){
                matches++;
            }
        }
        return matches;
    }

    /**
     * Helper to insert 2 both types of lists
     * @param insertKeys
     * @param insertList
     */
    private static void insertToList(int[] insertKeys, List<Integer> insertList) {
        for(int key: insertKeys){
            insertList.add(key);
        }
    }

    /**
     * Insert to map
     * @param insertKeys
     * @param insertMap
     */
    private static void insertToMap(int[] insertKeys, Map<Integer, Integer> insertMap) {
        for(int i = 0; i < insertKeys.length; i++){
            insertMap.put(insertKeys[i], i);
        }
    }

    /**
     * Generate 'howMany' integers upto range
     * @param howMany
     * @param range
     * @return
     */
    private static int [] generateRandomNumbers(int howMany, int range){

        int [] insertKeys = new int [howMany];

        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < howMany; i++){
            insertKeys[i] = random.nextInt(range) + 1;
        }

        return insertKeys;
    }

    /**
     * Main kicks off the experiment
     * @param args
     */
    public static void main (String [] args){
        timeTracker();
    }
}