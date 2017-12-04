package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public interface IComparison {

	public String compareMethod(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2);
}
