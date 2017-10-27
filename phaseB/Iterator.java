public interface Iterator<T> {

    /**
     * Check if the current object collection has another element.
     */
    public boolean hasAnotherElement();


    /**
     * Grab the next element of the collection.
     */
   public T nextElement();


}
