package comparison;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class HashMethod implements IHashMethod{
	
	private static List <Integer> hashvalueList1 = new ArrayList <> (); // list of hash values for each method : use hashmap instead of list
	private static List <Integer> hashvalueList2 = new ArrayList <> (); // list of hash values for each method : use hashmap instead of list
	
	
	/*
	 * Hash Function
	 */
	private static int CalHashValue(String x) {
		int hashVal = x.hashCode( );
        hashVal %= 10;
        if (hashVal < 0)
            hashVal += 10;
        return primeSize - hashVal % primeSize;
		
	}
	/*
	 *  Function to get prime number less than table size for CalHashValue function 
	 */
    private static int getPrime()
    {
        for (int i = 10000 - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
    
    private static int primeSize = getPrime();
	
    /*
	 * Helper function to get operators for given method
	 */
	private static int infixHelper( List <InfixExpression> infixList1 ) {
		List <String> operatorList1 = new ArrayList <> ();
		int i =0;
		int hashvalue = 0;
		for (InfixExpression infix : infixList1) {
			operatorList1.add(i, infix.getOperator().toString());
			hashvalue += CalHashValue(infix.getOperator().toString());
			i++;
		}
		
		return hashvalue;
	}
	
	/*
	 * Helper function to get prefix operators for given method
	 */
	private static int prefixHelper( List <PrefixExpression> prefixList1 ) {
		List <String> operatorList1 = new ArrayList <> ();
		int i =0;
		int hashvalue = 0;
		for (PrefixExpression prefix : prefixList1) {
			operatorList1.add(i, prefix.getOperator().toString());
			hashvalue += CalHashValue(prefix.getOperator().toString());
			i++;
		}
		
		return hashvalue;
	}
	
	/*
	 * Helper function to get prefix operators for given method
	 */
	private static int postfixHelper( List <PostfixExpression> postfixList1 ) {
		List <String> operatorList1 = new ArrayList <> ();
		int i =0;
		int hashvalue = 0;
		for (PostfixExpression postfix : postfixList1) {
			operatorList1.add(i, postfix.getOperator().toString());
			hashvalue += CalHashValue(postfix.getOperator().toString());
			i++;
		}
		
		return hashvalue;
	}
	
	public void hashComparison(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		
		/*
		 * calculating hash value for first method list
		 */
		for (MethodDeclaration n1: methodList1) {
			List <InfixExpression> infixList1 = new ArrayList <> ();
			List <PrefixExpression> prefixList1 = new ArrayList <> ();
			List <PostfixExpression> postfixList1 = new ArrayList <> ();
			infixList1.addAll(InfixExpressionFinder.perform(n1));
			hashvalueList1.add(infixHelper(infixList1));
			postfixList1.addAll(PostfixExpressionFinder.perform(n1));
			hashvalueList1.add(prefixHelper(prefixList1));
			prefixList1.addAll(PrefixExpressionFinder.perform(n1));
			hashvalueList1.add(postfixHelper(postfixList1));
		}
		
		/*
		 * calculating HashValue for second method list
		 */
		for (MethodDeclaration n2: methodList2) {
			List <InfixExpression> infixList2 = new ArrayList <> ();
			List <PrefixExpression> prefixList2 = new ArrayList <> ();
			List <PostfixExpression> postfixList2 = new ArrayList <> ();
			infixList2.addAll(InfixExpressionFinder.perform(n2));
			hashvalueList2.add(infixHelper(infixList2));
			postfixList2.addAll(PostfixExpressionFinder.perform(n2));
			hashvalueList2.add(prefixHelper(prefixList2));
			prefixList2.addAll(PrefixExpressionFinder.perform(n2));
			hashvalueList2.add(postfixHelper(postfixList2));
		}
	}
	
	public static List <Integer> getHashValueList1() {
		return hashvalueList1;
	}
	
	public static List <Integer> getHashValueList2() {
		return hashvalueList2;
	}
}
