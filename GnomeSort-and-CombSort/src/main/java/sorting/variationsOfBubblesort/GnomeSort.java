package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int length = rightIndex - leftIndex + 1;
		int pivot = leftIndex + 1;
		while (pivot < length) {
			if (array[pivot].compareTo(array[pivot -1]) == 1) {
				pivot++;
			} else {
				util.Util.swap(array, pivot, pivot-1);
				if (pivot > 1) {
					pivot--;
				} else {
					pivot ++;
				}
			}
		}

	}

	
}
