

import sorting.AbstractSorting;

public class RecursiveBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int length = rightIndex - leftIndex + 1;
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = leftIndex + 1; i < length -1; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					swap(array, i, i+1);
					swapped = true;
				}
			}
			for (int i = leftIndex; i < length -1; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					swap(array, i, i+1);
					swapped = true;
				}
			}
		}

	}

	public static void swap(Object[] array, int i, int j) {
		if (array == null) {
			throw new IllegalArgumentException();
		}
		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
