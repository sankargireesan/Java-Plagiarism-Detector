package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import astGenerator.GenerateAST;
import astGenerator.IGenerateAST;
import hashing.IHashMethod;
import main.Factory;

public class HashMethodTests {
	String curDir= System.getProperty("user.dir");
	String path1 = curDir+"\\src\\Tests\\testPackages\\";
	String path2 = curDir+"\\src\\Tests\\testPackages\\";
	
	@Before
	public void setup(){
		GenerateAST.clearForTests();
	}
	
	@Test
	public void testHashComparison() {
		Factory f = Factory.instance();
		IGenerateAST genAST = f.getGenAST();
		IHashMethod hM = f.getHashMethod();
		genAST.astGenerator(path1+"testFiles1",path2+"testFiles2");
		hM.hashComparison(genAST.getMethodList1(), genAST.getMethodList2());
		HashMap <String, List<Float>> testMap = hM.getComparisonList();
		List<Float> list = new ArrayList<Float>();
		list.add((float) 100);
		list.add((float) 3);
		list.add((float) 3);
		for (String str : testMap.keySet()) {
			assertEquals(list,testMap.get(str));
		}
	}
	
}
