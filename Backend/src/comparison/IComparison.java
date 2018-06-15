package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public interface IComparison {

	/**
	 * @param methodList1
	 * @param methodList2
	 * @return a string with all similar method names from given method list
	 */
	public String compareMethod(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2);
}
