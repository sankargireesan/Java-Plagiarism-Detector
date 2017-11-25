package testPackages.testFiles6;

public class SimilarMethod2 {

	public <T extends Comparable<T>> void recursiveSort(T[] arr, int a, int b) {
		if (a < b) {
			int part = randomizedPartition(arr, a, b);
			recursiveSort(arr, a, part - 1);
			recursiveSort(arr, part + 1, b);
		}
	}

	private <T> int randomizedPartition(T[] arr, int a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

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
