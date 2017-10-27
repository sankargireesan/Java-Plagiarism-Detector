/*
 * Abstract class of sub tree of the AST
 */
public abstract class ASTSubTree {
	
	static int Id;
	int Mass;
	int MassThreshold;
	
	/**
	* Returns unique identifier for each sub-tree.
	*/
	abstract public int getId();
	/**
	* Returns unique mass for each sub-tree.
	*/
	abstract public int getMass();
	/**
	* Returns unique mass threshold for each sub-tree.
	*/
	abstract public int getMassThreshold();
	/**
	* Sets mass for each sub-tree.
	*/
	abstract public void setMass();
	/**
	* Sets unique mass threshold for each sub-tree.
	*/
	abstract public void setMassThreshold();
	
}
