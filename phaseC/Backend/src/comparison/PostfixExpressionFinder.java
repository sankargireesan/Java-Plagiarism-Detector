package comparison;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class PostfixExpressionFinder extends ASTVisitor{
	private final List <PostfixExpression> expressions = new ArrayList <> ();
	
	/*
	 *	Returns a list of Prefix expression for the given AST node 
	 */
	public static List<PostfixExpression> perform(ASTNode node) {
		PostfixExpressionFinder finder = new PostfixExpressionFinder();
	    node.accept(finder);
	    return finder.getexpressions();
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.PostfixExpression)
	 */
	@Override
	 public boolean visit (final PostfixExpression var) {
	    expressions.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the postfix expressions discovered by this visitor
	   */
	  private List<PostfixExpression> getexpressions() {
	    return expressions;
	  }
}
