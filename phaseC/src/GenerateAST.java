import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jface.text.Document;

public class GenerateAST {
	
	//read file content into a string
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
	 
		//loop directory to get file list
		public static List<ASTNode> ParseFilesInDir(String path) throws IOException{
			File dirs = new File(".");
//			String dirPath = dirs.getCanonicalPath()+File.separator+"src"+File.separator;
			String dirPath = path+File.separator;

			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			String filePath = null;
	 
			List<ASTNode> nodeList = new ArrayList();
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 if(f.isFile()){
					 nodeList.add(parse(readFileToString(filePath)));
				 }
			 }
			 
			 return nodeList;
		}
	private static void print(ASTNode node) {
		 List properties= node.structuralPropertiesForType();
		 //System.out.println(properties.get(0));
		 //System.out.println("hahahaha");
		 /*
		 for (Iterator iterator= properties.iterator(); (iterator).hasNext();) {
			 Object descriptor= iterator.next();
			 if (descriptor instanceof SimplePropertyDescriptor) {
				 SimplePropertyDescriptor simple= (SimplePropertyDescriptor)descriptor;
				 Object value= node.getStructuralProperty(simple);
				 System.out.println(simple.getId() + " (" + value.toString() + ")");
			 } else if (descriptor instanceof ChildPropertyDescriptor) {
				 ChildPropertyDescriptor child= (ChildPropertyDescriptor)descriptor;
				 ASTNode childNode= (ASTNode)node.getStructuralProperty(child);
				 if (childNode != null) {
					 System.out.println("Child (" + child.getId() + ") {");
					 print(childNode);
					 System.out.println("}");
				 }
			 } else {
				 ChildListPropertyDescriptor list= (ChildListPropertyDescriptor)descriptor;
				 System.out.println("List (" + list.getId() + "){");
				 //print((List)node.getStructuralProperty(list));
				 System.out.println("}");
			 }
		 	}*/
		}
	
	public static List<ASTNode> getChildren(ASTNode node) {
		
		List <MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
		ASTVisitor visitor = new ASTVisitor(){
			
		    public boolean visit(MethodDeclaration node) {
				methods.add(node);	 
				
		    	return super.visit(node);
			}
		    public List<MethodDeclaration> getMethods() {
		    	return methods;
		    }
		};
		System.out.println(methods.size());
		
	    List<ASTNode> children = new ArrayList<ASTNode>();
	    List<?> list = node.structuralPropertiesForType();
	    //System.out.println(list.size());
	    for (int i = 0; i < list.size(); i++) {
	        Object child = node.getStructuralProperty((StructuralPropertyDescriptor)list.get(i));
	        if (child instanceof ASTNode) {
	        	
	        	ASTNode childType = (ASTNode)child;
//	        	System.out.println(((File) childType).getName().toStrinig());
	        	if(childType.getNodeType() == ASTNode.ASSIGNMENT){
	        		//System.out.println("Yesy");
	        		children.add(childType);
	        		}
	        }
	    }
	    
//	    System.out.println(children.size());
	    return children;
	}

	private static ASTNode parse(String string) {
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(string.toCharArray());
			//parser.setProject();
			parser.setResolveBindings(true);
			parser.setStatementsRecovery(true);
			ASTNode node = parser.createAST(null);
			//System.out.println(node.subtreeMatch(new ASTMatcher(), node));
//			System.out.println(node);
			return node;
			//System.out.println(node);
			//final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			//System.out.println(cu.types().get(0));
			
			//ASTNode an = new ASTNode;
		}
	
	public static String compareSimilarity(String path1, String path2) throws IOException{
		File dirs = new File(".");
		String dirPath = dirs.getCanonicalPath()+File.separator+"src"+File.separator;
		//System.out.println(dirPath);
		List<ASTNode> nodeList1 = ParseFilesInDir(dirPath);
		List<ASTNode> nodeList2 = ParseFilesInDir("E:\\workspace_AI\\HW4\\src\\sorters");
		List<MethodDeclaration> methodList1 = new ArrayList(); 
		List<MethodDeclaration> methodList2 = new ArrayList(); 
		
		for(ASTNode n: nodeList1){
			methodList1.addAll(MethodDeclarationFinder.perform(n));
//			methodList1.addAll(getChildren(n));
		}
		
		for(ASTNode n: nodeList2){
			methodList2.addAll(MethodDeclarationFinder.perform(n));
//			methodList2.addAll(getChildren(n));
		}
		
		System.out.println(methodList1.size());
		System.out.println(methodList2.size());
//		Iterator<ASTNode> it1 = (Iterator<ASTNode>) nodeList1.iterator();
//		Iterator<ASTNode> it2 = (Iterator<ASTNode>) nodeList2.iterator();

//		for(MethodDeclaration n1: methodList2){
//			System.out.println(n1.getName());
//		}
		
		List<String> similarMethod = new ArrayList<String>();
		
		for(MethodDeclaration n1: methodList1){
			for(MethodDeclaration n2: methodList2){
				if (n1.subtreeMatch(new ASTMatcher(), n2) == true) {
					//System.out.println("Similarity found in the method" +n1.getName());
					similarMethod.add(n1.getName().toString());
				}
			}
		}
		
		return similarMethod.toString();
		
	}
}


import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jface.text.Document;

public class GenerateAST {
	
	//read file content into a string
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
	 
		//loop directory to get file list
		public static void ParseFilesInDir() throws IOException{
			File dirs = new File(".");
			String dirPath = dirs.getCanonicalPath()+File.separator+"src"+File.separator;
	 
			File root = new File(dirPath);
			//System.out.println(rootDir.listFiles());
			File[] files = root.listFiles ( );
			String filePath = null;
	 
			 for (File f : files ) {
				 filePath = f.getAbsolutePath();
				 if(f.isFile()){
					 parse(readFileToString(filePath));
				 }
			 }
		}
	private static void print(ASTNode node) {
		 List properties= node.structuralPropertiesForType();
		 System.out.println(properties.get(0));
		 System.out.println("hahahaha");
		 /*
		 for (Iterator iterator= properties.iterator(); (iterator).hasNext();) {
			 Object descriptor= iterator.next();
			 if (descriptor instanceof SimplePropertyDescriptor) {
				 SimplePropertyDescriptor simple= (SimplePropertyDescriptor)descriptor;
				 Object value= node.getStructuralProperty(simple);
				 System.out.println(simple.getId() + " (" + value.toString() + ")");
			 } else if (descriptor instanceof ChildPropertyDescriptor) {
				 ChildPropertyDescriptor child= (ChildPropertyDescriptor)descriptor;
				 ASTNode childNode= (ASTNode)node.getStructuralProperty(child);
				 if (childNode != null) {
					 System.out.println("Child (" + child.getId() + ") {");
					 print(childNode);
					 System.out.println("}");
				 }
			 } else {
				 ChildListPropertyDescriptor list= (ChildListPropertyDescriptor)descriptor;
				 System.out.println("List (" + list.getId() + "){");
				 //print((List)node.getStructuralProperty(list));
				 System.out.println("}");
			 }
		 	}*/
		}

	private static void parse(String string) {
			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(string.toCharArray());
			//parser.setProject();
			parser.setResolveBindings(true);
			parser.setStatementsRecovery(true);
			ASTNode node = parser.createAST(null);
			System.out.println(node.subtreeMatch(new ASTMatcher(), node));
			System.out.println(node);
			print(node);
			//System.out.println(node);
			//final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			//System.out.println(cu.types().get(0));
			
			//ASTNode an = new ASTNode;
		}
	
	public static void main(String args[]) throws IOException{
		ParseFilesInDir();
	}
}

