/**
 * This class make the java collection API and our hash-sets API to be more alike
 */

import java.util.Collection;

public class CollectionFacadeSet implements SimpleSet {

    // every facade instance represents a collection
    private Collection<String> collection;

    /**
     * Builds a new CollectionFacadeSet
     * @param collection the collection this instance will hold
     */
    public CollectionFacadeSet(Collection<String> collection){
        this.collection = collection;
    }

    /**
     * Adding a new String to the set if it's not already there
     * @param newValue New value to add to the set
     * @return true if the String was added, false otherwise
     */
    public boolean add(String newValue){
        if (contains(newValue))
            return false;
        return collection.add(newValue);
    }

    /**
     * checks if searchVal is in the set
     * @param searchVal Value to search for
     * @return true if searchVal is in the set, false otherwise
     */
    public boolean contains(String searchVal){
        return collection.contains(searchVal);
    }

    /**
     * deletes a String from the set
     * @param toDelete Value to delete
     * @return true if toDelete was deleted, false if it was not there
     */
    public boolean delete(String toDelete){
        return collection.remove(toDelete);
    }

    /**
     * @return the number of elements in the set
     */
    public int size(){
        return collection.size();
    }
}
