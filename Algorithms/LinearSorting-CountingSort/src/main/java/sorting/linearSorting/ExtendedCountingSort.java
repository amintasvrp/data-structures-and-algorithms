package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 1) {
			countingSort(array, leftIndex, rightIndex);
		}
	}
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		int menor = array[getIndiceDoMenorElemento(array, leftIndex, rightIndex)];
		int maior = array[getIndiceDoMaiorElemento(array, leftIndex, rightIndex)];
		Integer[] aux = new Integer[maior - menor + 1];
		Integer[] arrayOrdenado = new Integer[rightIndex - leftIndex + 1];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = 0;
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			aux[array[i] - menor]++;
		}
		for(int i = 1; i < aux.length; i++){
			aux[i] += aux[i - 1];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayOrdenado[--aux[array[i] - menor]] = array[i];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = arrayOrdenado[i - leftIndex];
		}		
		
	}

	private int getIndiceDoMaiorElemento(Integer[] array, int left, int right) {
		int indiceDoMaior = left;
		for (int i = left + 1; i <= right; i++) {
			if (array[indiceDoMaior] < array[i]) {
				indiceDoMaior = i;
			}
		}
		return indiceDoMaior;
	}

	private int getIndiceDoMenorElemento(Integer[] array, int left, int right) {
		int indiceDoMenor = left;
		for (int i = left + 1; i <= right; i++) {
			if (array[indiceDoMenor] > array[i]) {
				indiceDoMenor = i;
			}
		}
		return indiceDoMenor;
	}

}
