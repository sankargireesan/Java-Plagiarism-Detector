package main.java;


import java.io.*;
import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import org.eclipse.jdt.core.dom.*;
//import org.eclipse.jface.text.Document;

public class GenerateAST {
	
	/*
	 * Read file content into a string
	 */
	public static String readFileToString(String filePath) throws IOException {
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
	public static List<ASTNode> ParseFilesInDir(String path) throws IOException{
//		String dirPath = path+File.separator;
//		File root = new File(dirPath);
//		File[] files = root.listFiles( );
//		String filePath = null;
//		String filePath1 = null;
//		List<ASTNode> nodeList = new ArrayList<>();
//		List<ASTNode> dirList = new ArrayList<>();
//		for(File f: files) {
//			filePath1 = f.getAbsolutePath();
//			if(f.isDirectory()) dirList.add(parse(read));
//		}
//		 for (File f : files ) {
//			 filePath = f.getAbsolutePath();
//			 if(f.isDirectory())
//				 files.
//			 if(f.isFile()){
//				 nodeList.add(parse(readFileToString(filePath)));
//			 }
//		 }
//		 
		 return getAllFiles(path+File.separator);
	}
	/*
	 * 
	 */
	public static List<ASTNode> getAllFiles(String dirPath) throws IOException {
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
	 * Method to get the children of the given Node
	 */
	public static List<ASTNode> getChildren(ASTNode node) {
		
		List<ASTNode> children = new ArrayList<ASTNode>();
	    List<?> list = node.structuralPropertiesForType();
	    for (int i = 0; i < list.size(); i++) {
	        Object child = node.getStructuralProperty((StructuralPropertyDescriptor)list.get(i));
	        if (child instanceof ASTNode) {
	        	
	        	ASTNode childType = (ASTNode)child;
	        	if(childType.getNodeType() == ASTNode.ASSIGNMENT){
	        		
	        		children.add(childType);
	        		}
	        }
	    }
	    
	    return children;
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
	 *  Function to get prime number less than table size for myhash2 function 
	 */
    public static int getPrime()
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
	private static int operatorHelper( List <InfixExpression> infixList1 ) {
		List <String> operatorList1 = new ArrayList <> ();
		int i =0;
		int hashvalue = 0;
		for (InfixExpression infix : infixList1) {
			operatorList1.add(i, infix.getOperator().toString());
			hashvalue += CalHashValue(infix.getOperator().toString());
			//System.out.println("Iter "+ i +" "+infix.getOperator().toString()+" "+infix.getOperator().hashCode());
			i++;
		}
		
		return hashvalue;
	}
	
	
	/*
	 * Main method
	 */
	public static String compareSimilarity(String path1, String path2) throws IOException{
		//File dirs = new File(".");
		
		//String dirPath = dirs.getCanonicalPath()+File.separator+"src"+File.separator;
		
//		System.out.println(dirPath);
//		List<ASTNode> nodeList1 = ParseFilesInDir("E:\\workspace_AI\\PhaseC\\src\\testPackage2");
//		System.out.println(nodeList1.size());
		
//		List<ASTNode> nodeList2 = ParseFilesInDir("E:\\workspace_AI\\PhaseC\\src\\testPackage");
		
		path1 = path1.replace("\\", "\\\\");
		path2 = path2.replace("\\", "\\\\");
		List<ASTNode> nodeList1 = ParseFilesInDir(path1);
		List<ASTNode> nodeList2 = ParseFilesInDir(path2);
		List<MethodDeclaration> methodList1 = new ArrayList(); 
		List<MethodDeclaration> methodList2 = new ArrayList();
		
		 
		List<VariableDeclarationFragment> variableList1 = new ArrayList<>(); 
		List<VariableDeclarationFragment> variableList2 = new ArrayList<>();
		
		List <PrefixExpression> prefixList1 = new ArrayList <> ();
		List <PostfixExpression> postfixList1 = new ArrayList <> ();
		
		List <Integer> hashvalueList = new ArrayList <> (); // list of hash values for each method : use hashmap instead of list
		List <Integer> hashvalueList2 = new ArrayList <> (); // list of hash values for each method : use hashmap instead of list
		for(ASTNode n: nodeList1){
			methodList1.addAll(MethodDeclarationFinder.perform(n));
			variableList1.addAll(VariableVisitor.perform(n));
			//infixList1.addAll(InfixExpressionFinder.perform(n));
			//postfixList1.addAll(PostfixExpressionFinder.perform(n));
			//prefixList1.addAll(PrefixExpressionFinder.perform(n));
		}
		//infixList1.addAll(InfixExpressionFinder.perform(methodList1.get(3)));
		
		/*
		 * calculating hashvalue for first method list
		 */
		for (MethodDeclaration n1: methodList1) {
			List <InfixExpression> infixList1 = new ArrayList <> ();
			infixList1.addAll(InfixExpressionFinder.perform(n1));
			hashvalueList.add(operatorHelper(infixList1));
			postfixList1.addAll(PostfixExpressionFinder.perform(n1));
			prefixList1.addAll(PrefixExpressionFinder.perform(n1));
		}
		
		
		for(ASTNode n: nodeList2){
			methodList2.addAll(MethodDeclarationFinder.perform(n));
			variableList2.addAll(VariableVisitor.perform(n));
		}
		
		/**
		 * calculating HashValue for second method list
		 */
		for (MethodDeclaration n2: methodList2) {
			List <InfixExpression> infixList2 = new ArrayList <> ();
			infixList2.addAll(InfixExpressionFinder.perform(n2));
			hashvalueList2.add(operatorHelper(infixList2));
			postfixList1.addAll(PostfixExpressionFinder.perform(n2));
			prefixList1.addAll(PrefixExpressionFinder.perform(n2));
		}

//		System.out.println(hashvalueList.size()+" "+hashvalueList2.size());
//		System.out.println(hashvalueList.get(0)+" "+hashvalueList2.get(0));
		List<String> similarMethod = new ArrayList<String>();
		
		for(VariableDeclarationFragment n: variableList1) {
			SimpleName newName = n.getAST().newSimpleName("variable");
			n.setName(newName);
		}
		
		for(VariableDeclarationFragment n: variableList2) {
			SimpleName newName = n.getAST().newSimpleName("variable");
			n.setName(newName);
		}
		
		
		for(MethodDeclaration n1: methodList1){
			
			for(MethodDeclaration n2: methodList2){
				if (n1.subtreeMatch(new ASTMatcher(), n2) == true) {
					similarMethod.add(n1.getName().toString());
					similarMethod.add(n2.getName().toString());
				}
			}
		}

		
		return similarMethod.toString();
		
	}
	
}

