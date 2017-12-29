/**
 * This class represents a closed hash set
 */
public class ClosedHashSet extends SimpleHashSet{

    // we use this String to identify whether the cell in the hashTable was used before
    private final String DELETED = "DELETED";
    // the hashTable of the closed set
    private String[] hashTable = new String[capacity];

    /**
     * Builds a new closed hash set using super constructor
     */
    public ClosedHashSet(){
    }

    /**
     * Builds a new closed hash set with the desired boundaries for the load factor
     * @param upperLoadFactor above this number, the set must do a rehashing to increase the capacity
     * @param lowerLoadFactor under this number, the set must do a rehashing to decrease the capacity
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
    }

    /**
     * Builds a new closed hash set, and adding data to the set
     * @param data data to insert into the set
     */
    public ClosedHashSet(String data[]){
        for (String str: data) {
            add(str);
        }
    }

    /**
     * Adding a new String to the set if it's not already there
     * @param newValue New value to add to the set
     * @return true if the String was added, false otherwise
     */
    @Override
    public boolean add(String newValue) {
        if (contains(newValue)){
            return false;
        }
        // newValue is not in the set
        int hash = newValue.hashCode();
        for (int i = 0;;i++){
            // i represents the i'th try to insert newValue to the set
            int currentIndex = hashFormula(hash, i, capacity);
            if (hashTable[currentIndex] == null || hashTable[currentIndex] == DELETED){
                // adding the newValue only when we find an empty cell
                hashTable[currentIndex] = newValue;
                break;
            }
        }
        elements++;
        if (loadFactor() > upperLoadFactor)
            rehashing();
        return true;
    }

    /*
     * This method is called only when the loadFactor is either too high or too small. Making the capacity
     * of the hash table x2 bigger or smaller respectively
     */
    private void rehashing() {
        if (this.loadFactor() < lowerLoadFactor)
            capacity /= 2;
        else if (this.loadFactor() > upperLoadFactor)
            capacity *= 2;
        // making new hash table in the desired size
        String[] newTable = new String[capacity];
        for (int i = 0; i < hashTable.length; i++){
            String currentElement = hashTable[i];
            if (currentElement == null || currentElement == DELETED){
                // nothing in this cell to be copied to the new hash table
                continue;
            }
            for (int j = 0;;j++){
                int hash = hashFormula(currentElement.hashCode(), j, newTable.length);
                if (newTable[hash] == null) {
                    newTable[hash] = currentElement;
                    break;
                }
            }
        }
        hashTable = newTable;
    }

    /**
     * checks if searchVal is in the set
     * @param searchVal Value to search for
     * @return true if searchVal is in the set, false otherwise
     */
    @Override
    public boolean contains(String searchVal) {
        int hash = searchVal.hashCode();
        for (int i = 0;; i++) {
            int index = hashFormula(hash, i, capacity);
            String currentElement = hashTable[index];
            if (currentElement == null)
                return false;
            if (currentElement == DELETED)
                // if it is DELETED, we don't want to return true in the next if in a case that DELETED
                // value is the same as searchVal
                continue;
            if (currentElement.equals(searchVal))
                return true;
        }
    }

    /**
     * deletes a String from the set
     * @param toDelete Value to delete
     * @return true if toDelete was deleted, false if it was not there
     */
    @Override
    public boolean delete(String toDelete) {
        int hash = toDelete.hashCode();
        for (int i = 0;;i++){
            int currentIndex = hashFormula(hash, i, capacity);
            String currentElement= hashTable[currentIndex];
            if (currentElement == null){
                // it should have been here, thus it is not in the set
                return false;
            }
            // if it is DELETED, we don't want to return true in the next if, in a case that DELETED
            // value is the same as toDelete
            if (currentElement.equals(toDelete) && currentElement != DELETED) {
                // we found it
                hashTable[currentIndex] = DELETED;
                break;
            }
        }
        elements--;
        if (loadFactor() < lowerLoadFactor && capacity != 1) {
            rehashing();
        }
        return true;
    }

    // given all the parameters, it returns the current possible place in the hashSet that we may try
    private int hashFormula(int hash, int tries, int tableLength){
        return (hash + (tries + tries * tries) / 2) & (tableLength - 1);
    }
}
