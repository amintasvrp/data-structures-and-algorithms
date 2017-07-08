package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int length = rightIndex - leftIndex + 1;
		int gap = length;
		boolean trocou = true;
		while(trocou || gap >1){
			if (gap > 1) {
				gap = (int) (gap/1.3);
			}			
			trocou = false;
			for (int i = 0; i < length - gap; i++) {
				if (array[leftIndex + i].compareTo(array[gap + i]) == 1) {
					util.Util.swap(array, leftIndex + i, gap + i);
					trocou = true;
				}
			}
			
		}
				
	}
}
