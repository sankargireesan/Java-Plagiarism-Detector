package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import astGenerator.GenerateAST;


public class TestASTGenerator {

    String curDir= System.getProperty("user.dir");
    String path1 = curDir+"\\src\\Tests\\testPackages\\";
    String path2 = curDir+"\\src\\Tests\\testPackages\\";

    @Before
    public void setup(){
        GenerateAST.clearForTests();
    }

    @Test
    public void testASTGenerator(){
        GenerateAST AST1 = new GenerateAST();
        AST1.astGenerator(path1+"testFilesASTGenerator", path2+ "testFilesASTGenerator2");
        assertEquals(AST1.getMethodList1().size(),
                AST1.getMethodList2().size());
        assertEquals(AST1.getMethodList1().get(0).getName().toString(), "add");
        assertEquals(AST1.getMethodList1().get(1).getName().toString(), "minus");
        
        assertEquals(AST1.getVariableList1().get(0).getName().toString(),
                "a");
        assertEquals(AST1.getVariableList1().get(1).getName().toString(),
        		"b");
        
        assertEquals(AST1.getMethodList2().get(0).getName().toString(), "add");
        assertEquals(AST1.getMethodList2().get(1).getName().toString(), "minus");
        
        assertEquals(AST1.getVariableList2().get(0).getName().toString(),
                "a");
        assertEquals(AST1.getVariableList2().get(1).getName().toString(),
        		"b");
    }


    @Test
    public void testASTGeneratorWithNoMethod(){
        GenerateAST AST1 = new GenerateAST();
        AST1.astGenerator(path1+"testFilesASTGenerator3", path2+"testFilesASTGenerator4");
        assertEquals(AST1.getMethodList1().size(), 0);
        assertEquals(AST1.getMethodList2().size(), 0);
        assertEquals(AST1.getVariableList1().get(0).getName().toString(),
                "a");
        assertEquals(AST1.getVariableList1().get(1).getName().toString(),
                "b");
        assertEquals(AST1.getMethodList1().toString(),"[]");
        assertEquals(AST1.getMethodList2().toString(),"[]");
    }


    @Test
    public void testASTGeneratorWithDifferentMethods(){
        GenerateAST AST2 = new GenerateAST();
        AST2.astGenerator(path1+"testFilesASTGenerator", path2+"testFilesASTGenerator5");
        assertEquals(AST2.getMethodList2().get(0).getName().toString(), "times");
        assertEquals(AST2.getMethodList1().get(0).getName().toString(),
                "add");
        assertEquals(AST2.getVariableList1().get(1).getName().toString(),
                "b");
    }



}
