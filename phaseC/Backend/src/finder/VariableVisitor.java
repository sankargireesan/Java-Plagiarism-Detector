package finder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;


public class VariableVisitor extends ASTVisitor {
	private final List <VariableDeclarationFragment> variables = new ArrayList <> ();
	
	/**
	 * @param AST node
	 * @return variable declaration fragment for the given AST Node
	 */
	public List<VariableDeclarationFragment> perform(ASTNode node) {
		VariableVisitor finder = new VariableVisitor();
	    node.accept(finder);
	    return finder.getVariables();
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.VariableDeclarationFragment)
	 */
	@Override
	public boolean visit (final VariableDeclarationFragment var) {
	    variables.add (var);
	    return super.visit(var);
	}

	  /**
	   * @return an immutable list view of the variables discovered by this visitor
	   */
	  private List<VariableDeclarationFragment> getVariables() {
	    return variables;
	  }
	
}
