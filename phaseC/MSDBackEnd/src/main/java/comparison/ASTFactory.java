package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ASTFactory {
	
	private IGenerateAST genAST;
	private IComparison  compAST;
	private IVariableRename varRename;
	private IHashMethod hashMethod;
	
	ASTFactory(){	
		genAST = new GenerateAST();
		compAST = new Comparison();
		varRename = new VariableRename();
		hashMethod = new HashMethod();
	}

	public void astGenerator(String path1, String path2) {
		genAST.astGenerator(path1, path2);
	}
	
	public List<MethodDeclaration> getMethodList1() {
		return genAST.getMethodList1();
	}
	
	public List<MethodDeclaration> getMethodList2() {
		return genAST.getMethodList2();
	}
	
	public List<VariableDeclarationFragment> getVariableList1() {
		return genAST.getVariableList1();
	}
	
	public List<VariableDeclarationFragment> getVariableList2() {
		return genAST.getVariableList2();
	}
	
	public String compareMethod(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		return compAST.compareMethod(methodList1, methodList2);
	}
	
	public List<VariableDeclarationFragment> renameVar (List<VariableDeclarationFragment> variableList){
		return varRename.renameVar(variableList);
	}
	
	public void hashComparison(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		hashMethod.hashComparison(methodList1,methodList2);
	}
}
