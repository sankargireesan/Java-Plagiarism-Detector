package Tests.testPackages.testFiles10.innerPackage;

public class similarMethod {

	public int myCompareTo(Comparable c1, Comparable c2){
		if(c1 == null){
			return -1;
		}
		else if(c2 == null){
			return 1;
		}
		else return c1.compareTo(c2);
	}
	
}
