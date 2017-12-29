/**
 * This class allows closedHashSet to have an array of linked lists
 */

import java.util.Iterator;
import java.util.LinkedList;

public class ListWrapper {

    // linked list that will hold the Strings of the closed hash table
    private LinkedList<String> list;

    /**
     * Builds a new ListWrapper
     */
    public ListWrapper(){
       list = new LinkedList<String>();
    }

    /**
     * adding new String to the list
     * @param toAdd the String we want to add
     */
    public void add(String toAdd){
        list.addLast(toAdd);
    }

    /**
     * @return returns an iterator to the list
     */
    public Iterator<String> getIterator(){
        return list.iterator();
    }

    /**
     * checks if searchVal is in the list
     * @param searchVal the String we want to search
     * @return true if searchVal is in the list, false otherwise
     */
    public boolean contains(String searchVal){
        return list.contains(searchVal);
    }
}
