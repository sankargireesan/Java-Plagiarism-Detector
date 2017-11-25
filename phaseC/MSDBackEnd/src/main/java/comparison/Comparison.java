package comparison;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;
public class Comparison implements IComparison{
	/*
	 * compare method for comparison of ASTs
	 */
	public String compareMethod(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		
		List<String> similarMethod = new ArrayList<String>();
		
		for(MethodDeclaration n1: methodList1){
			for(MethodDeclaration n2: methodList2){
				if (n1.subtreeMatch(new ASTMatcher(), n2) == true) {
					similarMethod.add(n1.getName().toString());
					similarMethod.add(n2.getName().toString());
				}
			}
		}				
		return similarMethod.toString();
	}
}
