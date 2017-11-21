package main.java;


/**
* A generic set
*/
interface Set<T> {
	/**
	* Add an element
	*/
	void add(T t1); 
	/**
	* Check if an element occurs in the set
	*/
	boolean IsMember(T t); 
	/**
	* Remove an element
	*/
	void remove(T t); 
	/**
	* Return the number of arguments in the set
	*/
	int size(); 
	/**
	* Return an iterator over the set
	*/
	Iterator<T> iterator();
}