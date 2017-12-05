package comparison;

import java.util.List;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public interface IVariableRename {
	/**
	 * @param variableList
	 * variable names are renamed to same name "variable"
	 */
	public List<VariableDeclarationFragment> renameVar (List<VariableDeclarationFragment> variableList);
}
