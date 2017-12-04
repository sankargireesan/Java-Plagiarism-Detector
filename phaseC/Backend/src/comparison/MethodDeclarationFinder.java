package comparison;


import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationFinder extends ASTVisitor {
	  private final List <MethodDeclaration> methods = new ArrayList <> ();
	  /*
	   * Returns a list of Prefix expression for the given AST node 
	   */
	  public List<MethodDeclaration> perform(ASTNode node) {
	      MethodDeclarationFinder finder = new MethodDeclarationFinder();
	      node.accept(finder);
	      return finder.getMethods();
	  }
	  /*
	   * (non-Javadoc)
	   * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.MethodDeclaration)
	   */
	  @Override
	  public boolean visit (final MethodDeclaration method) {
	    methods.add (method);
	    return super.visit(method);
	  }

	  /**
	   * @return an immutable list view of the methods discovered by this visitor
	   */
	  private List <MethodDeclaration> getMethods() {
	    return methods;
	  }
	}
