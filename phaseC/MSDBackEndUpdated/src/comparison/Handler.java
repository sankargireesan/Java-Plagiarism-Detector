package comparison;
/*
 * Main handler class to handle different AST comparison functions
 */
public class Handler {
	
	public static String astComparisonHandler(String path1, String path2) {
		
		ASTFactory f = new ASTFactory();
		
		f.astGenerator(path1, path2);
		f.compareMethod(f.getMethodList1(), f.getMethodList2());
		f.renameVar(f.getVariableList1());
		f.renameVar(f.getVariableList2());
		f.compareMethod(f.getMethodList1(), f.getMethodList2());
		f.hashComparison(f.getMethodList1(), f.getMethodList2());

//		HashMethod.getHashValueList1();
//		HashMethod.getHashValueList2();
		
		return f.compareMethod(f.getMethodList1(), f.getMethodList2());
		
	}

	/*public static void main(String[] args) {
		String path1 = args[0];
		String path2 = args[1];
		System.out.println(astComparisonHandler(path1,path2));
	}*/
	
	public static void main(String[] args) {
		String curDir= System.getProperty("user.dir");
		String path1 = curDir+"\\src\\main\\java\\testPackages\\";
		String path2 = curDir+"\\src\\main\\java\\testPackages\\";
		
		System.out.println(astComparisonHandler(path1+"testFiles3",path2+"testFiles4"));
	}
}
