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

