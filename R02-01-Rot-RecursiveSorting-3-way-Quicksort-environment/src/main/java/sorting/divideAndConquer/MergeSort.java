package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		T[] aux = util.Util.makeArrayOfComparable(rightIndex - leftIndex + 1);
		mergeSort(array, aux, leftIndex, rightIndex);
	}

	private void mergeSort(T[] array, T[] aux, int inicio, int fim) {
		if (inicio < fim) {
			int meio = (inicio + fim)/2;
			mergeSort(array, aux, inicio, meio);
			mergeSort(array, aux, meio + 1, fim);
			merge(array, aux, inicio, meio, fim);
		}
		
	}

	private void merge(T[] array, T[] aux, int inicio, int meio, int fim) {
		for (int i = inicio; i <= fim; i++) {
			aux[i] = array[i];
		}
		int x = inicio;
		int y = meio + 1;
		for (int i = inicio; i <= fim; i++) {
			if (x > meio) {
				array[i] = aux[y++];
			} else if (y > fim) {
				array[i] = aux[x++];
			} else if (aux[x].compareTo(aux[y]) == -1) {
				array[i] = aux[x++];
			} else {
				array[i] = aux[y++];
			}
		}
		
	}
}
