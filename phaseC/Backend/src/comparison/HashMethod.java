package comparison;
/*
 * This class performs hash comparison
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;


public class HashMethod implements IHashMethod{
	
	private static HashMap <String, List<Integer>> hashvalueList1 = new HashMap<> ();
	private static HashMap <String, List<Integer>> hashvalueList2 = new HashMap<> ();
	private static HashMap <String, List<Float>> comparisonList = new HashMap<> ();
	
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
	/*
	 * Returns the number of expressions in a method
	 */
	private int numberOfExpressions(MethodDeclaration method) {
		List <InfixExpression> infixList = new ArrayList <> ();
		List <PrefixExpression> prefixList = new ArrayList <> ();
		List <PostfixExpression> postfixList = new ArrayList <> ();
		
		int size = 0;
		infixList.addAll(InfixExpressionFinder.perform(method));
		postfixList.addAll(PostfixExpressionFinder.perform(method));
		prefixList.addAll(PrefixExpressionFinder.perform(method));
		size = infixList.size() + postfixList.size() + prefixList.size();
		return size;
	}
	
	/*
	 * Returns the hash value for expressions of the given method
	 */
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
	
	/*
	 *  Returns a list of parameters of a method
	 */
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
	
	/*
	 * Returns the hash value of parameters of a method
	 */
	private int parameterHash (MethodDeclaration m) {
		List<String> para = getParameters(m);
		int p = 0;
		for (String string : para) {
			p += CalHashValue(string);
		}
		return p;
	}
	
	/*
	 * Calculates the percentage of two integers
	 */
	private float percentage (int number1, int number2) {
		return ((float)Math.min(number1, number2)/(float)Math.max(number1, number2))*100;
	}
	
	/*
	 * Returns percentage of similarity
	 */
	private void percentageComparison (HashMap <String, List<Integer>> hashvalueList1, HashMap <String, List<Integer>> hashvalueList2) {
		
		for (String str1 : hashvalueList1.keySet()) {
			for (String str2 : hashvalueList2.keySet()) {
				List<Float> list = new ArrayList<Float>();
				float percent = percentage(hashvalueList1.get(str1).get(0), hashvalueList2.get(str2).get(0));
				float expressPercent = percentage( hashvalueList1.get(str1).get(1), hashvalueList2.get(str2).get(1));
				if(percent >=80.0 && expressPercent >= 90.0) {
					list.add(percent);
					list.add((float)hashvalueList1.get(str1).get(1));
					list.add((float)hashvalueList2.get(str2).get(1));
					comparisonList.put(str1+"~HMS~"+str2, list);
				}
			}
		}
	}
	
	
	/*
	 * Performs the hashComparison on list of methods
	 */
	@Override
	public void hashComparison(List<MethodDeclaration> methodList1, List<MethodDeclaration> methodList2) {			
		
		for (MethodDeclaration n1: methodList1) {
			List<Integer> list = new ArrayList<Integer>();
			int n = numberOfExpressions(n1);
			int rT = CalHashValue(n1.getReturnType2().toString());
			int eH = expressionHash(n1);
			int pH = parameterHash(n1);
			list.add(rT+eH+pH);
			list.add(n);
			hashvalueList1.put( n1.getName().toString()+"\n\n"+ n1.toString(),list);
		}
		
		
		//calculating HashValue for second method list
		for (MethodDeclaration n2: methodList2) {
			List<Integer> list2 = new ArrayList<Integer>();
			int n = numberOfExpressions(n2);
			int rT = CalHashValue(n2.getReturnType2().toString());
			int eH = expressionHash(n2);
			int pH = parameterHash(n2);
			list2.add(rT+eH+pH);
			list2.add(n);
			hashvalueList2.put( n2.getName().toString() +"\n\n"+ n2.toString(), list2 );
		}
		// calculating the percentage
		percentageComparison(hashvalueList1,hashvalueList2);
	}
	
	/*
	 * Returns the hash map with similarity percentage and number of expressions in each method
	 */
	@Override
	public HashMap <String, List<Float>> getComparisonList() {
		return comparisonList;
	}
}
