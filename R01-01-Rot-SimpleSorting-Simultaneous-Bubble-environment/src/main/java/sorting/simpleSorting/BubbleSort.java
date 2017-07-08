package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = rightIndex; i > leftIndex; i++) {
			for (int j = leftIndex + 1; j < i; j++) {
				if (array[j].compareTo(array[j - 1]) == -1) {
					util.Util.swap(array, j - 1, j);
				}
			}
		}
	}

}
