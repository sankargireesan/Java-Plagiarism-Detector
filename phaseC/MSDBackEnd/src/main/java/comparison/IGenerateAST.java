package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public interface IGenerateAST {

	public void astGenerator(String path1, String path2);

	public List<MethodDeclaration> getMethodList1();

	public List<MethodDeclaration> getMethodList2();

	public List<VariableDeclarationFragment> getVariableList1();

	public List<VariableDeclarationFragment> getVariableList2();
}