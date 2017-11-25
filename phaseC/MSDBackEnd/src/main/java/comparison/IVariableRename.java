package comparison;

import java.util.List;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public interface IVariableRename {
	public List<VariableDeclarationFragment> renameVar (List<VariableDeclarationFragment> variableList);
}
