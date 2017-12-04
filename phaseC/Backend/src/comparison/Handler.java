package comparison;

import java.util.HashMap;
import java.util.List;

/*
 * Main handler class to handle different AST comparison functions
 */
public class Handler {
	/*
	 * Returns string of similar methods
	 */
	public static String astComparisonHandler(String path1, String path2) {
		StringBuilder sb = new StringBuilder();
		Factory f = Factory.instance();
		IGenerateAST genAST = f.getGenAST();
		IComparison compAST=f.getCompAST();
		IVariableRename varRename=f.getVarRename();
		IHashMethod hashMethod=f.getHashMethod();
		
		genAST.astGenerator(path1, path2);
		sb.append(compAST.compareMethod(genAST.getMethodList1(), genAST.getMethodList2())+" ");
		varRename.renameVar(genAST.getVariableList1());
		varRename.renameVar(genAST.getVariableList2());
		sb.append(compAST.compareMethod(genAST.getMethodList1(), genAST.getMethodList2()));
		hashMethod.hashComparison(genAST.getMethodList1(), genAST.getMethodList2());
		HashMap <String, List<Float>> hM = hashMethod.getComparisonList();
		
		for (String str : hM.keySet()) {
			sb.append(" "+str);
		}
		return sb.toString();
	}

	/*public static void main(String[] args) {
		String path1 = args[0];
		String path2 = args[1];
		System.out.println(astComparisonHandler(path1,path2));
	}*/
	
	public static void main(String[] args) {
		String curDir= System.getProperty("user.dir");
		String path1 = curDir+"\\src\\Tests\\testPackages\\";
		String path2 = curDir+"\\src\\Tests\\testPackages\\";
		
		System.out.println(astComparisonHandler(path1+"testFiles3",path2+"testFiles4"));
	}
}
