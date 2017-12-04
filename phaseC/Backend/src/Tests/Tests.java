package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import comparison.*;


public class Tests {

	String curDir= System.getProperty("user.dir");
	String path1 = curDir+"\\src\\Tests\\testPackages\\";
	String path2 = curDir+"\\src\\Tests\\testPackages\\";
	
	@Before
	public void setup(){
		GenerateAST.clearForTests();
	}
	
	@Test
	public void exactFunction() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles1",path2+"testFiles2");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
	}

	@Test
	public void similarFunctionsDifferentVariables() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles3",path2+"testFiles4");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
	}
	
	@Test
	public void differentPackages() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles5",path2+"testFiles6");
		assertEquals("[recursiveSort, recursiveSort, myCompareTo, myCompareTo]",similarMethod);  
	}
	
	
	@Test
	public void noSimilarity() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles7",path2+"testFiles8");
		assertEquals("[]",similarMethod);
	}
	
	@Test
	public void withInnerProjects() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles9",path2+"testFiles10");
		assertEquals("[sort, sort, recursiveSort, recursiveSort, randomizedPartition, randomizedPartition, partition, partition, myCompareTo, myCompareTo]",similarMethod);
	}


}