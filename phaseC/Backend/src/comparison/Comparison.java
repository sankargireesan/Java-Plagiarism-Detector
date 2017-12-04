package comparison;

import java.util.List;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Comparison Implements interface IComparison
 */
public class Comparison implements IComparison{
	
	/**
	 * @given two lists of method
	 * @return returns a String containing all similar methods
	 */
	public String compareMethod(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		
		// StringBuilder to store the similar methods
		StringBuilder sb = new StringBuilder();
		for(MethodDeclaration n1: methodList1){
			for(MethodDeclaration n2: methodList2){
				if (n1.subtreeMatch(new ASTMatcher(), n2)) {
					sb.append(n1.getName().toString()+" ");//adding similar method name of first repository
					sb.append(n2.getName().toString()+" ");//adding similar method name of second repository
				}
			}
		}				
		return sb.toString();
	}
}
