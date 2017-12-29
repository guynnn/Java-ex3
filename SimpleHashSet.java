/**
 * This class represent an abstract class with basic function that a set must have
 */
public abstract class SimpleHashSet implements SimpleSet {

    /** the size of the set */
    protected int elements;
    /** the capacity the set has */
    protected int capacity;
    /** a lower boundry - under that loadFactor the set must do a rehashing */
    protected float lowerLoadFactor;
    /** an upper boundry - under that loadFactor the set must do a rehashing */
    protected float upperLoadFactor;

    /**
     * Builds a new SimpleHashSet
     */
    protected SimpleHashSet(){
        capacity = 16;
        lowerLoadFactor = 0.25f;
        upperLoadFactor = 0.75f;
    }

    /**
     * @return The load factor of the set
     */
    public float loadFactor(){
        return (float)elements / capacity;
    }

    /**
     * @return the number of elements in the set
     */
    public int size(){
        return elements;
    }

    /**
     * @return the capacity of the set
     */
    public int capacity(){
        return capacity;
    }
}
