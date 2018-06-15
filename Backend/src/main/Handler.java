package main;

import java.util.HashMap;
import java.util.List;

import astGenerator.IGenerateAST;
import comparison.IComparison;
import hashing.IHashMethod;
import renaming.IVariableRename;

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
		sb.append(compAST.compareMethod(genAST.getMethodList1(), genAST.getMethodList2()));
		varRename.renameVar(genAST.getVariableList1());
		varRename.renameVar(genAST.getVariableList2());
		sb.append("~VC~");
		sb.append(compAST.compareMethod(genAST.getMethodList1(), genAST.getMethodList2()));
		hashMethod.hashComparison(genAST.getMethodList1(), genAST.getMethodList2());
		HashMap <String, List<Float>> hM = hashMethod.getComparisonList();
		sb.append("~HC~");
		for (String str : hM.keySet()) {
			sb.append(str+"~NM~");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String path1 = args[0];
		String path2 = args[1];
		System.out.println(astComparisonHandler(path1,path2));
	}
	
}
