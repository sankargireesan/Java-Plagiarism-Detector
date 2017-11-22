package comparison;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.*;

public class GenerateAST {
	
	private static List<MethodDeclaration> methodList1 = new ArrayList <>(); 
	private static List<MethodDeclaration> methodList2 = new ArrayList <>();	 
	private static List<VariableDeclarationFragment> variableList1 = new ArrayList<>(); 
	private static List<VariableDeclarationFragment> variableList2 = new ArrayList<>();
	
	/*
	 * Read file content into a string
	 */
	private static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
	 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
	 
		reader.close();
	
		return  fileData.toString();	
	}
	 
	/*
	 * Loop directory to get file list
	 */
	private static List<ASTNode> ParseFilesInDir(String path) throws IOException{
		 return getAllFiles(path+File.separator);
	}
	
	/*
	 * returns list of ast node of all files
	 */
	private static List<ASTNode> getAllFiles(String dirPath) throws IOException {
		    File directory = new File(dirPath);
		    List<ASTNode> nodeList = new ArrayList<>();
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	        for (File file : fList){
	            if (file.isFile()){
	            	nodeList.add(parse(readFileToString(file.getAbsolutePath())));
	                
	            } else if (file.isDirectory()){
	            	nodeList.addAll(getAllFiles(file.getAbsolutePath()));
	            
	        }
	    }
	        return nodeList;
	}
	
	/*
	 * Method to parse the given string
	 */
	private static ASTNode parse(String string) {
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(string.toCharArray());
			parser.setResolveBindings(true);
			parser.setStatementsRecovery(true);
			ASTNode node = parser.createAST(null);
			return node;
	}
	
		
	/*
	 * list of ast is generated in this method
	 */
	public static void astGenerator(String path1, String path2) {
		
		path1 = path1.replace("\\", "\\\\");
		path2 = path2.replace("\\", "\\\\");
		List<ASTNode> nodeList1 = null;
		
		try {
			nodeList1 = ParseFilesInDir(path1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<ASTNode> nodeList2 = null;
		
		try {
			nodeList2 = ParseFilesInDir(path2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(ASTNode n: nodeList1){
			methodList1.addAll(MethodDeclarationFinder.perform(n));
			variableList1.addAll(VariableVisitor.perform(n));
		}
				
		for(ASTNode n: nodeList2){
			methodList2.addAll(MethodDeclarationFinder.perform(n));
			variableList2.addAll(VariableVisitor.perform(n));
		}
	}
	
	public static List<MethodDeclaration> getMethodList1() {
		return methodList1;
	}
	
	public static List<MethodDeclaration> getMethodList2() {
		return methodList2;
	}
	
	public static List<VariableDeclarationFragment> getVariableList1() {
		return variableList1;
	}
	
	public static List<VariableDeclarationFragment> getVariableList2() {
		return variableList2;
	}
}

