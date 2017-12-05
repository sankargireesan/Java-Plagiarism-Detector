package comparison;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.InfixExpression;

public class InfixExpressionFinder extends ASTVisitor{
	private final List <InfixExpression> expressions = new ArrayList <> ();
	
	/**
	 *	Returns a list of Prefix expression for the given AST node 
	 */
	public static List<InfixExpression> perform(ASTNode node) {
		InfixExpressionFinder finder = new InfixExpressionFinder();
	    node.accept(finder);
	    return finder.getexpressions();
	}
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.InfixExpression)
	 */
	@Override
	 public boolean visit (final InfixExpression var) {
	    expressions.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the infix expressions discovered by this visitor
	   */
	  private List<InfixExpression> getexpressions() {
	    return expressions;
	  }
}
