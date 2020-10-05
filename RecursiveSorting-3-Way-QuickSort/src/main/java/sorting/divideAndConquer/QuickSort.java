package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivot = quick(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot - 1);
			sort(array, pivot + 1, rightIndex);
		}
	}

	private int quick(T[] array, int inicio, int fim) {
		int i = inicio + 1;
		int f = fim;
        while (i <= f) {
               if (array[i].compareTo(array[inicio]) <= 0){
            	   i++;
               } else if (array[f].compareTo(array[inicio]) == 1){
            	   f--;
               } else {
            	   util.Util.swap(array, i, f);
                   i++;
                   f--;
               }
        }
        util.Util.swap(array, inicio, f);
        return f;
	}
}
