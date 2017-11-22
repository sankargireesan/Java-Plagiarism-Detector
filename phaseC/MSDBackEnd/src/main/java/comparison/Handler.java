package comparison;
/*
 * Main handler class to handle different ast comparison functions
 */
public class Handler {
	public static String astComparisonHandler(String path1, String path2) {
		
		GenerateAST.astGenerator(path1, path2);
		Comparison.compareMethod(GenerateAST.getMethodList1(), GenerateAST.getMethodList2());
		VariableRename.renameVar(GenerateAST.getVariableList1());
		VariableRename.renameVar(GenerateAST.getVariableList2());
		Comparison.compareMethod(GenerateAST.getMethodList1(), GenerateAST.getMethodList2());
		HashMethod.hashComparison(GenerateAST.getMethodList1(), GenerateAST.getMethodList2());
		HashMethod.getHashValueList1();
		HashMethod.getHashValueList2();
		
		return Comparison.compareMethod(GenerateAST.getMethodList1(), GenerateAST.getMethodList2()).toString();
		
	}
}
