/**
 * This class represents an open hash set
 */
import java.util.Iterator;

public class OpenHashSet extends SimpleHashSet {

    // this is the hash table. Every ListWrapper has a linked list in it
    private ListWrapper[] hashTable = new ListWrapper[capacity];

    /**
     * Builds a new open hash set
     * @param upperLoadFactor above this number, the set must do a rehashing to increase the capacity
     * @param lowerLoadFactor under this number, the set must do a rehashing to decrease the capacity
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
    }

    /**
     * Builds a new open hash set, and adding data to the set
     * @param data data to insert into the set
     */
    public OpenHashSet(String[] data){
        for (String str: data) {
            add(str);
        }
    }

    /**
     * Builds a new open set using super constructor
     */
    public OpenHashSet(){
    }

    /**
     * Adding a new String to the set if it's not already there
     * @param newValue New value to add to the set
     * @return true if the String was added, false otherwise
     */
    @Override
    public boolean add(String newValue) {
        if (contains(newValue)) {
            return false;
        }
        // it is not in the set, lets add it
        elements++;
        int index = newValue.hashCode() & (hashTable.length - 1);
        // if it's the first String to be added to this bucket, it is null
        if (hashTable[index] == null)
            hashTable[index] = new ListWrapper();
        hashTable[index].add(newValue);
        if (loadFactor() > upperLoadFactor)
            rehashing();
        return true;
    }

    /**
     * checks if searchVal is in the set
     * @param searchVal Value to search for
     * @return true if searchVal is in the set, false otherwise
     */
    @Override
    public boolean contains(String searchVal) {
        int hash = searchVal.hashCode() & (hashTable.length - 1);
        if (hashTable[hash] == null)
            // if this bucket is empty, it's not there for sure
            return false;
        return hashTable[hash].contains(searchVal);
    }

    /**
     * deletes a String from the set
     * @param toDelete Value to delete
     * @return true if toDelete was deleted, false if it was not there
     */
    @Override
    public boolean delete(String toDelete) {
        int index = toDelete.hashCode() & (hashTable.length - 1);
        if (hashTable[index] == null || !hashTable[index].contains(toDelete))
            // it is not in the set
            return false;
        // it is in the set, creating an iterator to find it in the linked list
        Iterator<String> iterator = hashTable[index].getIterator();
        while(iterator.hasNext()){
            if (iterator.next().equals(toDelete)){
                iterator.remove();
                break;
            }
        }
        elements--;
        // if the capacity is 1, no need to do a rehashing because it will make a mess
        if (this.loadFactor() < lowerLoadFactor && capacity != 1)
            rehashing();
        return true;
    }

    /**
     * This method is called only when the loadFactor is either too high or too small. Making the capacity
     * of the hash table x2 bigger or smaller respectively
     */
    private void rehashing() {
        if (this.loadFactor() < lowerLoadFactor)
            capacity /= 2;
        else if (this.loadFactor() > upperLoadFactor)
            capacity *= 2;
        // making new hash table in the desired size
        ListWrapper[] newTable = new ListWrapper[capacity];
        // now we start to copy elements from the old table to the new one
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null)
                // if hashTable[i] is null, there's nothing there to copy to the new table
                continue;
            Iterator<String> iterator = hashTable[i].getIterator();
            while (iterator.hasNext()){
                String current = iterator.next();
                int index = current.hashCode() & (newTable.length - 1);
                if (newTable[index] == null){
                    newTable[index] = new ListWrapper();
                }
                newTable[index].add(current);
            }
        }
        hashTable = newTable;
    }
}
