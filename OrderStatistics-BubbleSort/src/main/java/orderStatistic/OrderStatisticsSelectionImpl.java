package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a
	 * estrategia de usar o selection sem modificar o array original. Note que
	 * seu algoritmo vai apenas selectionar a estatistica deordem e nao ordenar
	 * o array original.
	 * 
	 * Restricoes: - Preservar o array original, ou seja, nenhuma modificacao
	 * pode ser feita no array original - Nenhum array auxiliar deve ser criado
	 * e utilizado. - Caso a estatistica de ordem nao exista no array, o
	 * algoritmo deve retornar null. - Considerar que k varia de 1 a N -
	 * Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		return orderStatistics(array, k - 1);
	}

	private T orderStatistics(T[] array, int k) {
		T orderStatistic = null;
		if (k == 0) {
			int smaller = 0;
			for (int i = 1; i < array.length; i++) {
				if (array[i].compareTo(array[smaller]) < 0) {
					smaller = i;
				}
			}
			orderStatistic = array[smaller];
		} else {
			T previousSmaller = orderStatistics(array, k - 1);
			int smaller;
			if (array[0].equals(previousSmaller)) {
				smaller = 1;
			} else {
				smaller = 0;
			}
			for (int i = 0; i < array.length; i++) {
				if (array[i].compareTo(array[smaller]) < 0 && previousSmaller.compareTo(array[i]) < 0) {
					smaller = i;
				}
			}
			orderStatistic = array[smaller];
		}
		return orderStatistic;

	}

}
