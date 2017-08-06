package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode<T>(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode<T>(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue != null && height <= maxHeight) {
			SkipListNode<T>[] update = new SkipListNode[maxHeight];
			SkipListNode<T> aux = root;
			for (int i = maxHeight - 1; i >= 0; i--) {
				while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
					aux = aux.getForward(i);
				}
				update[i] = aux;
			}
			aux = aux.getForward(0);
			if (aux.getKey() == key) {
				aux.setValue(newValue);
			} else {
				aux = new SkipListNode<T>(key, height, newValue);
				for (int i = 0; i < height; i++) {
					if (update[i].getForward() == null) {
						aux.setForward(i, NIL);
					} else {
						aux.setForward(i, update[i].getForward(i));
					}
					update[i].setForward(i, aux);
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			int i = 0;
			while (i < aux.height()) {
				if (!(update[i].getForward(i).equals(aux))) {
					break;
				}
				update[i].setForward(i, aux.getForward(i));
				i++;
			}

		}
	}

	@Override
	public int height() {
		int height = 0;
		SkipListNode<T>[] array = toArray();
		for (int i = 1; i < array.length - 1; i++) {
			if (height < array[i].height()) {
				height = array[i].height();
			}
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = root;
		SkipListNode<T> result = null;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
				aux = aux.getForward(i);
			}
		}
		aux = aux.getForward(0);
		if (aux.getKey() == key) {
			result = aux;
		}
		return result;
	}

	@Override
	public int size() {
		return sizeRecursive(root);
	}

	private int sizeRecursive(SkipListNode<T> node) {
		int size = 0;
		if (!(node.getForward(0).equals(NIL))) {
			size = 1 + sizeRecursive(node.getForward(0));
		}
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] result = new SkipListNode[size() + 2];
		result[0] = root;
		toArrayRecursive(result, root.getForward(0), 1);
		result[size() + 1] = NIL;
		return result;
	}

	private void toArrayRecursive(SkipListNode<T>[] result, SkipListNode<T> node, int i) {
		if (!(node.equals(NIL))) {
			result[i] = node;
			toArrayRecursive(result, node.forward[0], i + 1);
		}
	}
}
