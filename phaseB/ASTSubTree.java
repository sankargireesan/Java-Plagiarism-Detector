/*
 * Abstract class of sub tree of the AST
 */
public abstract class ASTSubTree {
	
	static int Id;
	float Mass;
	float Similarity;
	float MassThreshold;
	
	/**
	* Returns unique identifier for each sub-tree.
	*/
	abstract public int getId();
	/**
	* Returns unique mass for each sub-tree.
	*/
	abstract public int getMass();
	/**
	* Returns unique similarity for pair of sub-trees.
	*/
	abstract public int getSimilarity();
	/**
	* Returns unique mass threshold for each sub-tree.
	*/
	abstract public int getMassThreshold();
	
}
