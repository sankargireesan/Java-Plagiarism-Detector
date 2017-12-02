package comparison;

public class AbstractFactory {

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
