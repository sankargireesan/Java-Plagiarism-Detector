package testPackages.testFiles1;

public class SimilarMethod1 {

	public void sort(Comparable[] list) {
		int start_list_index = 0;

		int end_list_index = list.length - 1;

		recursiveSort(list, start_list_index, end_list_index);

	}

	public <T extends Comparable<T>> void recursiveSort(T[] arr, int a, int b) {
		if (a < b) {
			int part = randomizedPartition(arr, a, b);
			recursiveSort(arr, a, part - 1);
			recursiveSort(arr, part + 1, b);
		}
	}

	
	public <T extends Comparable<T>> int randomizedPartition(T[] arr, int a,
			int b) {
		int random_index = (int) Math.floor(Math.random() * (b - a + 1) + a);
		T tem = arr[b];
		arr[b] = arr[random_index];
		arr[random_index] = tem;
		return partition(arr, a, b);
	}

	public <T extends Comparable<T>> int partition(T[] arr, int a, int b) {
		T pivot_elem = arr[b]; // we will take last element as pivot element
		int indx = a - 1;

		for (int i = a; i < b; i++) {
			

				if (myCompareTo(arr[i],pivot_elem) < 0) {
					indx++;
					T temp = arr[indx];
					arr[indx] = arr[i];
					arr[i] = temp;
				}
					}

		T temptwo = arr[indx + 1];
		arr[indx + 1] = arr[b];
		arr[b] = temptwo;
		return indx + 1;

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
