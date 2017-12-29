/**
 * This Class analyzing the running time of five different collections.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    // Holding the collection we are about to test
    private static SimpleSet[] collections = new SimpleSet[5];
    // Array of Strings that have the same hashCode
    private static String[] data1 = Ex3Utils.file2array("data1.txt");
    // Array of Strings that have evenly distributed hashCode
    private static String[] data2 = Ex3Utils.file2array("data2.txt");
    // The word that we want to check if it's in the given data
    private static String wordToSearch = "23";
    // Number that use to convert from nanoSeconds to milliSeconds
    private static final int NANO_TO_MILLI = 1000000;
    // Number of iteration the collection will perform in the contain function (excluding linkedList)
    private static int loops = 70000;
    // Number of iteration the linkedList will perform in the contain function
    private static int linkedListLoops = 7000;

    /**
     * runs the tests
     * @param args no need to use this
     */
    public static void main(String[] args){
        // Initializing the cells to five collection we want to test
        collections[0] = new OpenHashSet();
        collections[1] = new ClosedHashSet();
        collections[2] = new CollectionFacadeSet(new TreeSet<>());
        collections[3] = new CollectionFacadeSet(new LinkedList<>());
        collections[4] = new CollectionFacadeSet(new HashSet<>());
        // Adding data to the collection
        System.out.println(addAll(collections[4], data1));
        // Testing the contains function
        contain(collections[0] ,wordToSearch , data2);
    }

    /*
     * Adding all the elements in data to the given collection
     * collection -  one of the five collection in the collections array
     * data - the data to be loaded into the collection
     * return The time it took to add the given data in milliSeconds
     */
    private static int addAll(SimpleSet collection, String[] data){
        long start = System.nanoTime() / NANO_TO_MILLI;
        for (int j = 0; j < data.length; j++) {
            collection.add(data[j]);
        }
        long end = System.nanoTime() / NANO_TO_MILLI;
        return (int)(end - start);
    }

    /*
     * Checking if word is in collection.
     * collection -  the collection to test
     * word - the word to test
     * data -  the data to be loaded into the collection before the test start
     */
    private static void contain(SimpleSet collection, String word,String[] data) {
        addAll(collection, data);
        // if collection is linkedList - less iterations needed
        int iterations = (collection == collections[3]) ? linkedListLoops : loops;
        // if collection is linkedList - warm-up is not needed
        if (collection != collections[3])
            numOfContain(collection, word, iterations);
        // Now the test starts
        long start = System.nanoTime();
        numOfContain(collection, word, iterations);
        long end = System.nanoTime();
        System.out.println((end - start) / iterations);
    }

    /*
     * Doing numOfIterations amount of contains in order to get a better results
     */
    private static void numOfContain(SimpleSet collection, String word, int numOfIterations){
        for (int i = 0; i < numOfIterations; i++){
            collection.contains((word));
        }
    }
}