package Tests.testPackages.testFiles8;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class VariableVisitor extends ASTVisitor {
	private final List <VariableDeclarationFragment> variables = new ArrayList <> ();
	
	public static List<VariableDeclarationFragment> perform(ASTNode node) {
		VariableVisitor finder = new VariableVisitor();
	    node.accept(finder);
	    return finder.getVariables();
	}
	
	 public boolean visit (final VariableDeclarationFragment var) {
	    variables.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the variables discovered by this visitor
	   */
	  public List<VariableDeclarationFragment> getVariables() {
	    return variables;
	  }
	
}
