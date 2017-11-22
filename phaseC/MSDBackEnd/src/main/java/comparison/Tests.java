package comparison;

import static org.junit.Assert.*;
import org.junit.Test;

public class Tests {

	String curDir= System.getProperty("user.dir");
	String path1 = curDir+"\\src\\main\\java\\";
	String path2 = curDir+"\\src\\main\\java\\";
	
	@Test
	public void exactFunction() {
		
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles1",path2+"testFiles2");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
		GenerateAST.clearForTests();
	}

	@Test
	public void similarFunctionsDifferentVariables() {
		
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles3",path2+"testFiles4");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
		GenerateAST.clearForTests();
	}
	
	@Test
	public void differentPackages() {
		
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles5",path2+"testFiles6");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
		GenerateAST.clearForTests();
	}
	
	
	@Test
	public void noSimilarity() {
		
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles7",path2+"testFiles8");
		assertEquals("[]",similarMethod);  
		GenerateAST.clearForTests();
	}


}
