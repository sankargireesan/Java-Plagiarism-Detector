package comparison;

public class Factory {
	
	private static Factory _factory = null;
	
	private Factory() {}
	
	public static Factory instance() {
		if(_factory==null) {
			_factory= new Factory();
		}
		return _factory;
	}
	
    public IGenerateAST getGenAST() {
        return new GenerateAST();
    }

    public IComparison getCompAST() {
        return new Comparison();
    }

    public IVariableRename getVarRename() {
        return new VariableRename();
    }

    public IHashMethod getHashMethod() {
        return new HashMethod();
    }
}
