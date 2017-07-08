package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			tail = (tail + 1) % (array.length);
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			head = (head + 1) % (array.length);
			T dequeued = array[head];
			elements--;
			return dequeued;
		}
	}

	@Override
	public T head() {
		T header = null;
		if (!(isEmpty())) {
			header = array[(head + 1) % (array.length)];
		}
		return header;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

}
