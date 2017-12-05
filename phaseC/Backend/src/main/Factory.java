package main;

import astGenerator.GenerateAST;
import astGenerator.IGenerateAST;
import comparison.Comparison;
import comparison.IComparison;
import hashing.HashMethod;
import hashing.IHashMethod;
import renaming.IVariableRename;
import renaming.VariableRename;

/**
 * Factory is a Singleton class
 * 
 */
public class Factory {
	
	private static Factory _factory = null;
	
	private Factory() {}
	
	/**
	 * creates an object of Factory if not present already
	 * @return object of Factory
	 */
	public static Factory instance() {
		if(_factory==null) {
			_factory= new Factory();
		}
		return _factory;
	}
	
	/**
	 * @return object of a class which implements IGenerateAST
	 */
    public IGenerateAST getGenAST() {
        return new GenerateAST();
    }

	/**
	 * @return object of a class which implements IComparison
	 */
    public IComparison getCompAST() {
        return new Comparison();
    }

	/**
	 * @return object of a class which implements IVariableRename
	 */
    public IVariableRename getVarRename() {
        return new VariableRename();
    }

	/**
	 * @return object of a class which implements IHashMethod
	 */
    public IHashMethod getHashMethod() {
        return new HashMethod();
    }
}
