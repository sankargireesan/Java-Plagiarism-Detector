package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import astGenerator.GenerateAST;
import renaming.VariableRename;

public class TestRenameVariables {
    String curDir= System.getProperty("user.dir");
    String path1 = curDir+"\\src\\Tests\\testPackages\\";
    String path2 = curDir+"\\src\\Tests\\testPackages\\";

    @Before
    public void setup(){
        GenerateAST.clearForTests();
    }

	@Test
	public void testRenameVariables() {
		GenerateAST AST1 = new GenerateAST();
        AST1.astGenerator(path1+"testFilesASTGenerator", path2+ "testFilesASTGenerator2");
        VariableRename vr1 = new VariableRename();
        vr1.renameVar(AST1.getVariableList1());
        assertEquals(AST1.getVariableList1().get(0).getName().toString(), "variable");
        assertEquals(AST1.getVariableList1().get(1).getName().toString(), "variable");
	}
	
	@Test
	public void testRenamingWithNoVariables() {
		GenerateAST AST1 = new GenerateAST();
        AST1.astGenerator(path1+"testFilesASTGenerator", path2+ "testFilesASTRenaming");
        VariableRename vr1 = new VariableRename();
        vr1.renameVar(AST1.getVariableList2());
        assertEquals(AST1.getVariableList2().toString(), "[]");
	}

}
