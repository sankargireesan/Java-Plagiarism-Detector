import java.util.Iterator;

public interface Set<T> {

    /**
     * Add an object to the current set.
     * @param t the object to be added.
     */

    public void add(T t);


    /**
     * Add a set of objects to the current set.
     * @param tset the set to be added.
     */
    public void addAll (Set<T> tset);

    /**
     * Check if the set contains a certain object.
     * @param t the object to be checked.
     * @return yes if the set contains the object and no otherwise.
     */

    boolean contains(T t);

    /**
     * Remove an object from the set.
     * @param t the object to be removed.
     */

    void remove(T t);

    /**
     * Return the size of the set.
     * @return the size.
     */

    int size();

    /**
     * Return an iterator of the set.
     * @return an iterator that iterate over the set.
     */

    Iterator<T> iterator();

}
