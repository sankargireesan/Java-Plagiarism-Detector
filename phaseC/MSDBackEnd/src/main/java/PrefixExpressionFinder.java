package main.java;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class PrefixExpressionFinder extends ASTVisitor{
private final List <PrefixExpression> expressions = new ArrayList <> ();
	
	public static List<PrefixExpression> perform(ASTNode node) {
		PrefixExpressionFinder finder = new PrefixExpressionFinder();
	    node.accept(finder);
	    return finder.getexpressions();
	}
	
	 public boolean visit (final PrefixExpression var) {
	    expressions.add (var);
	    return super.visit(var);
	  }

	  /**
	   * @return an immutable list view of the prefix expressions discovered by this visitor
	   */
	  public List<PrefixExpression> getexpressions() {
	    return expressions;
	  }
}
