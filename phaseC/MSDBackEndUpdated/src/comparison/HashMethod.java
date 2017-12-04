package comparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.*;

public class HashMethod implements IHashMethod{
	
	private static HashMap <String, Integer> hashvalueList1 = new HashMap<> (); // list of hash values for each method : use hashmap instead of list
	private static HashMap <String, Integer> hashvalueList2 = new HashMap<> (); // list of hash values for each method : use hashmap instead of list
	
	
	/*
	 * Hash Function
	 */
	private int CalHashValue(String x) {
		int hashVal = x.hashCode( );
        hashVal %= 10;
        if (hashVal < 0)
            hashVal += 10;
        return primeSize - hashVal % primeSize;
		
	}
	/*
	 *  Function to get prime number less than table size for CalHashValue function 
	 */
    private int getPrime()
    {
        for (int i = 1000 - 1; i >= 1; i--)
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
    
    private int primeSize = getPrime();
	
    /*
	 * Helper function to get operators for given method
	 */
	private int infixHelper( List <InfixExpression> infixList1 ) {
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
	private int prefixHelper( List <PrefixExpression> prefixList1 ) {
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
	private int postfixHelper( List <PostfixExpression> postfixList1 ) {
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
	
	private int expressionHash(MethodDeclaration method) {
		List <InfixExpression> infixList = new ArrayList <> ();
		List <PrefixExpression> prefixList = new ArrayList <> ();
		List <PostfixExpression> postfixList = new ArrayList <> ();
		infixList.addAll(InfixExpressionFinder.perform(method));
		int temp = infixHelper(infixList);
		postfixList.addAll(PostfixExpressionFinder.perform(method));
		temp += prefixHelper(prefixList);
		prefixList.addAll(PrefixExpressionFinder.perform(method));
		temp += postfixHelper(postfixList);
		return temp;
	}
	
	
	private List<String> getParameters (MethodDeclaration m) {
		List<String> sb = new ArrayList<String>();
		String s;
		List<?> l = m.parameters();
		for(int i =0; i< l.size();i++) {
			s = m.parameters().get(i).toString();
			sb.add(m.parameters().get(i).toString().substring(0,s.indexOf(" ")));
		}
		
		return sb;
	}
	
	private int parameterHash (MethodDeclaration m) {
		List<String> para = getParameters(m);
		int p = 0;
		for (String string : para) {
			p += CalHashValue(string);
		}
		return p;
	}
	
	public void hashComparison(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {
		
		/*
		 * calculating hash value for first method list
		 */
		for (MethodDeclaration n1: methodList1) {
			
			int rT = CalHashValue(n1.getReturnType2().toString());
			int eH = expressionHash(n1);
			int pH = parameterHash(n1);
			hashvalueList1.put(n1.getName().toString(),(rT + eH + pH ));
		}
//		System.out.println(methodList1.size());
//		for (Integer m : hashvalueList1.values()) {
//			System.out.println(m);
//		}
		/*
		 * calculating HashValue for second method list
		 */
		for (MethodDeclaration n2: methodList2) {
			int rT = CalHashValue(n2.getReturnType2().toString());
			int eH = expressionHash(n2);
			int pH = parameterHash(n2);
			hashvalueList2.put(n2.getName().toString(), rT + eH + pH );
		}
//		for (Integer m : hashvalueList2.values()) {
//			System.out.println(m);
//		}
	}
	
	public static HashMap <String,Integer> getHashValueList1() {
		return hashvalueList1;
	}
	
	public static HashMap <String,Integer> getHashValueList2() {
		return hashvalueList2;
	}
}
