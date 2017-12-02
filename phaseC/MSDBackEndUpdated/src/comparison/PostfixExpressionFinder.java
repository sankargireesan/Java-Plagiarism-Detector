package comparison;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class PostfixExpressionFinder extends ASTVisitor{
private final List <PostfixExpression> expressions = new ArrayList <> ();
	
	public static List<PostfixExpression> perform(ASTNode node) {
		PostfixExpressionFinder finder = new PostfixExpressionFinder();
	    node.accept(finder);
	    return finder.getexpressions();
	}
	
	 public boolean visit (final PostfixExpression var) {
	    expressions.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the postfix expressions discovered by this visitor
	   */
	  public List<PostfixExpression> getexpressions() {
	    return expressions;
	  }
}
