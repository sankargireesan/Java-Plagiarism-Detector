package main.java;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class InfixExpressionFinder extends ASTVisitor{
private final List <InfixExpression> expressions = new ArrayList <> ();
	
	public static List<InfixExpression> perform(ASTNode node) {
		InfixExpressionFinder finder = new InfixExpressionFinder();
	    node.accept(finder);
	    return finder.getexpressions();
	}
	
	 public boolean visit (final InfixExpression var) {
	    expressions.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the infix expressions discovered by this visitor
	   */
	  public List<InfixExpression> getexpressions() {
	    return expressions;
	  }
}
