package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import comparison.IGenerateAST;
import comparison.IHashMethod;
import comparison.Factory;
import comparison.GenerateAST;

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
	
	//@Test
	public void testHashComparison2() {
		Factory f2 = Factory.instance();
		IGenerateAST genAST = f2.getGenAST();
		IHashMethod hM = f2.getHashMethod();
		genAST.astGenerator(path1+"testFiles4",path2+"testFiles3");
		hM.hashComparison(genAST.getMethodList1(), genAST.getMethodList2());
		HashMap <String, List<Float>> testMap = hM.getComparisonList();
		List<List<Float>> list = new ArrayList<List<Float>>();
		List<Float> list1 = new ArrayList<Float>();
		list1.add((float) 100);
		list1.add((float) 3);
		list1.add((float) 3);
		List<Float> list2 = new ArrayList<Float>();
		list2.add((float) 100);
		list2.add((float) 3);
		list2.add((float) 3);
		List<Float> list3 = new ArrayList<Float>();
		list3.add((float) 99.92466);
		list3.add((float) 1);
		list3.add((float) 1);
		list.add(list1);
		list.add(list2);
		list.add(list3);
	}
	
}
