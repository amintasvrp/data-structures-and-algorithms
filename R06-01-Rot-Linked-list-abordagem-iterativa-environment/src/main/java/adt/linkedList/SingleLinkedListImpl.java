package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (head.isNIL()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxNode = getHead();
		while (!(auxNode.isNIL())) {
			size++;
			auxNode = auxNode.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = getHead();
		while (!(node.isNIL()) && !(node.getData().equals(element))) {
			node = node.getNext();
		}
		T result = node.getData();
		return result;		
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> auxHead = getHead();
			if (head.isNIL()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, head);
				head = newHead;
			} else {
				while (!(auxHead.getNext().isNIL())) {
					auxHead = auxHead.getNext();
				}
				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, auxHead.getNext());
				auxHead.setNext(newNode);
			}
		}

	}

	@Override
	public void remove(T element) {
		if (getHead().getData().equals(element)) {
			head = head.getNext();
		} else {
			SingleLinkedListNode<T> auxNode = head;
			SingleLinkedListNode<T> prevNode = null;
			while (!(auxNode.isNIL()) && !(auxNode.getData().equals(element))) {
				prevNode = auxNode;
				auxNode = auxNode.getNext();
			}
			if (!(auxNode.isNIL())) {
				prevNode.setNext(auxNode.getNext());
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> auxNode = getHead();
		int index = 0;
		while (!(auxNode.isNIL())) {
			result[index] = auxNode.getData();
			auxNode = auxNode.getNext();
			index++;
		}
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
