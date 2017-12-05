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
	public void noSimilarity() {
		String similarMethod = Handler.astComparisonHandler(path1+"testFiles7",path2+"testFiles8");
		assertEquals("~VC~~HC~",similarMethod);
	}

}
