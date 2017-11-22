package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class VariableRename {
	public static List<VariableDeclarationFragment> renameVar (List<VariableDeclarationFragment> variableList) {	
		for(VariableDeclarationFragment n: variableList) {
			SimpleName newName = n.getAST().newSimpleName("variable");
			n.setName(newName);
		}
	return variableList;
	}
}
