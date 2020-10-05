package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 1) {
			countingSort(array, leftIndex, rightIndex);
		}
	}
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[getIndiceDoMaiorElemento(array, leftIndex, rightIndex)];
		Integer[] aux = new Integer[maior + 1];
		Integer[] arrayOrdenado = new Integer[array.length];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = 0;
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			aux[array[i]]++;
		}
		for(int i = 1; i < aux.length; i++){
			aux[i] += aux[i - 1];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayOrdenado[--aux[array[i]]] = array[i];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = arrayOrdenado[i - leftIndex];
		}		
		
	}

	private int getIndiceDoMaiorElemento(Integer[] array, int left, int right) {
		int indiceDoMaior = left;
		for (int i = left; i <= right; i++) {
			if (array[indiceDoMaior] < array[i]) {
				indiceDoMaior = i;
			}
		}
		return indiceDoMaior;
	}

}
