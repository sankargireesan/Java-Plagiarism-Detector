package main.java;


/*
 * Interface for pair of sub tree
 */
public interface PairSet<T> {
	/**
	* Add a pair to the list
	*/
	void addPair(T t1, T t2, T[] list); 
	/**
	* Check if pair element occurs in the list
	*/
	boolean IsMember(T t1, T t2); 
	/**
	* Remove a pair from the list
	*/
	void remove(T t1, T t2); 
	/**
	* Return the number of pairs in the list
	*/
	int size(); 
	/**
	* Return an iterator over the list
	*/
	Iterator<T> iterator();
}
