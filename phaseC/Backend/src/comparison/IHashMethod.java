package comparison;

import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
/*
 * Interface to implement hash comparison method
 */
public interface IHashMethod {
	
	/**
	 * Performs the hashComparison on list of methods
	 */
	public void hashComparison(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2);
	/**
	 * Returns the hash map with similarity percentage and number of expressions in each method
	 */
	public HashMap <String, List<Float>> getComparisonList();
}
