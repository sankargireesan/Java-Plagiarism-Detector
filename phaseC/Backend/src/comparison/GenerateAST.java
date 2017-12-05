package comparison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * GenerateAST implements IGenerateAST
 *
 */
public class GenerateAST implements IGenerateAST {

	/**
	 * @usage: stores all methods and variables present in the given project repository
	 */
	private static List<MethodDeclaration> methodList1 = new ArrayList<>();
	private static List<MethodDeclaration> methodList2 = new ArrayList<>();
	private static List<VariableDeclarationFragment> variableList1 = new ArrayList<>();
	private static List<VariableDeclarationFragment> variableList2 = new ArrayList<>();

	/**
	 * Read file content and returns it as String
	 */
	private String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	/**
	 * Loop directory to get file list
	 */
	private List<ASTNode> ParseFilesInDir(String path) throws IOException {
		return getAllFiles(path + File.separator);
	}

	/**
	 * @returns: list of AST node of all files present in the given directory
	 * and in the sub directories
	 */
	private List<ASTNode> getAllFiles(String dirPath) throws IOException {
		File directory = new File(dirPath);
		List<ASTNode> nodeList = new ArrayList<>();
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				nodeList.add(parse(readFileToString(file.getAbsolutePath())));

			} else if (file.isDirectory()) {
				// get files from sub directories
				nodeList.addAll(getAllFiles(file.getAbsolutePath()));
			}
		}
		return nodeList;
	}

	/**
	 * Method to parse the given String
	 * @return an AST node representing the given String
	 */
	private ASTNode parse(String string) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(string.toCharArray());
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(true);
		ASTNode node = parser.createAST(null);
		return node;
	}

	/**
	 * generates a list of ASTNodes for the files present in the given input path
	 * @effect: methods are added to respective methodList
	 * 			variables are added to respective variableList
	 */			
	public void astGenerator(String path1, String path2) {
		MethodDeclarationFinder mf = new MethodDeclarationFinder();
		VariableVisitor vf = new VariableVisitor();
		path1 = path1.replace("\\", "\\\\");
		path2 = path2.replace("\\", "\\\\");
		List<ASTNode> nodeList1 = null;
		List<ASTNode> nodeList2 = null;

		try {
			nodeList1 = ParseFilesInDir(path1);
		} catch (IOException e) {
			return;
		}
		
		try {
			nodeList2 = ParseFilesInDir(path2);
		} catch (IOException e) {
			return;
		}

		for (ASTNode n : nodeList1) {
			methodList1.addAll(mf.perform(n));
			variableList1.addAll(vf.perform(n));
		}

		for (ASTNode n : nodeList2) {
			methodList2.addAll(mf.perform(n));
			variableList2.addAll(vf.perform(n));
		}
	}

	/**
	 * getter method for methodList1
	 */
	public List<MethodDeclaration> getMethodList1() {
		return methodList1;
	}


	/**
	 * getter method for methodList2
	 */
	public List<MethodDeclaration> getMethodList2() {
		return methodList2;
	}


	/**
	 * getter method for variableList1
	 */
	public List<VariableDeclarationFragment> getVariableList1() {
		return variableList1;
	}


	/**
	 * getter method for variableList2
	 */
	public List<VariableDeclarationFragment> getVariableList2() {
		return variableList2;
	}

	
	/**
	 * For testing purpose
	 * effect: reseting method lists and variable lists
	 */
	public static void clearForTests() {
		methodList1.clear();
		methodList2.clear();
		variableList1.clear();
		variableList2.clear();
		HashMethod.clearComparisonList();
	}
}
