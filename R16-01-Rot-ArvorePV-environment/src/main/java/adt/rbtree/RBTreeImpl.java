package adt.rbtree;

import adt.bst.BSTImpl;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.node = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeightRecursive((RBNode<T>) getRoot());
	}

	private int blackHeightRecursive(RBNode<T> node) {
		int result = 0;
		if (!(node.isEmpty())) {
			if (node.getColour() == Colour.BLACK) {
				result++;
			}
			result += blackHeightRecursive((RBNode<T>) node.getRight());
		}
		return result;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) node).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodesRecursive((RBNode<T>) getRoot());
	}

	private boolean verifyChildrenOfRedNodesRecursive(RBNode<T> node) {
		boolean result = true;
		if (!(node.isEmpty())) {
			if (node.getColour() == Colour.RED && !(((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
					&& ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK)) {
				result = false;
			} else {
				result = verifyChildrenOfRedNodesRecursive((RBNode<T>) node.getLeft())
						&& verifyChildrenOfRedNodesRecursive((RBNode<T>) node.getRight());
			}
		}
		return result;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (!(getRoot().isEmpty())) {
			if (blackHeightRecursive((RBNode<T>) node.getRight()) != blackHeightRecursive((RBNode<T>) node.getLeft())) {
				throw new RuntimeException();
			}
		}
		return true;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			insertRecursive((RBNode<T>) getRoot(), value);
		}
	}

	private void insertRecursive(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if ((value.compareTo(node.getData()) < 0)) {
				insertRecursive((RBNode<T>) node.getLeft(), value);
			} else if ((value.compareTo(node.getData()) > 0)) {
				insertRecursive((RBNode<T>) node.getRight(), value);
			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] array = (RBNode<T>[]) new RBNode[size()];
		rbPreOrderRecursive((RBNode<T>) getRoot(), array);
		return array;
	}

	private void rbPreOrderRecursive(RBNode<T> node, RBNode<T>[] array) {
		if (!node.isEmpty()) {
			add(array, node);
			rbPreOrderRecursive((RBNode<T>) node.getLeft(), array);
			rbPreOrderRecursive((RBNode<T>) node.getRight(), array);
		}
	}

	private void add(RBNode<T>[] array, RBNode<T> node) {
		int i = 0;
		while (i < array.length) {
			if (array[i] == null) {
				array[i] = node;
				break;
			} else {
				i++;
			}
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(getRoot())) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		if (grandParent != null && parent.getData().compareTo(grandParent.getData()) < 0
				&& (grandParent.getRight()) != null && ((RBNode<T>) grandParent.getRight()).getColour() == Colour.RED) {
			RBNode<T> uncleRight = (RBNode<T>) grandParent.getRight();
			(parent).setColour(Colour.BLACK);
			(uncleRight).setColour(Colour.BLACK);
			(grandParent).setColour(Colour.RED);
			fixUpCase1(grandParent);
		} else if (grandParent != null && parent.getData().compareTo(grandParent.getData()) > 0
				&& (grandParent.getLeft()) != null && ((RBNode<T>) grandParent.getLeft()).getColour() == Colour.RED) {
			RBNode<T> uncleLeft = (RBNode<T>) grandParent.getLeft();
			(parent).setColour(Colour.BLACK);
			(uncleLeft).setColour(Colour.BLACK);
			(grandParent).setColour(Colour.RED);
			fixUpCase1(grandParent);
		} else
			fixUpCase4(node);
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> auxNode = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		if (grandParent != null) {
			if ((parent.getData().compareTo(grandParent.getData()) < 0)
					&& (node.getData().compareTo(parent.getData()) > 0)) {
				adt.bt.Util.leftRotation(parent);
				auxNode = (RBNode<T>) node.getLeft();
				resetRoot(auxNode);
			} else if ((parent.getData().compareTo(grandParent.getData()) > 0)
					&& (node.getData().compareTo(parent.getData()) < 0)) {
				adt.bt.Util.rightRotation(parent);
				auxNode = (RBNode<T>) node.getRight();
				resetRoot(auxNode);
			}

		}
		fixUpCase5(auxNode);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandParent = (RBNode<T>) parent.getParent();
		if (grandParent != null) {
			parent.setColour(Colour.BLACK);
			grandParent.setColour(Colour.RED);
			if (node.getData().compareTo(parent.getData()) < 0) {
				adt.bt.Util.rightRotation(grandParent);
				resetRoot(grandParent);
			} else {
				adt.bt.Util.leftRotation(grandParent);
				resetRoot(grandParent);
			}
		}
	}

	private void resetRoot(RBNode<T> node) {
		if (node != null) {
			RBNode<T> nodeAux = node;
			while (nodeAux.getParent() != null) {
				nodeAux = (RBNode<T>) nodeAux.getParent();
			}
			setRoot(nodeAux);
		}
	}

	protected int countBlackNodes() {
		return countBlackNodesRecursive((RBNode<T>) getRoot());
	}

	private int countBlackNodesRecursive(RBNode<T> node) {
		int result = 0;
		if (!(node.isEmpty())) {
			if (node.getColour() == Colour.BLACK) {
				result++;
			}
			int left = countBlackNodesRecursive((RBNode<T>) node.getLeft());
			int right = countBlackNodesRecursive((RBNode<T>) node.getRight());
			result += left + right;
		}
		return result;
	}

	protected int redNodesCount() {
		return redNodesCount((RBNode<T>) node);
	}

	protected int redNodesCount(RBNode<T> node) {
		int result = 0;

		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				result++;
			}

			result += redNodesCount((RBNode<T>) node.getLeft());
			result += redNodesCount((RBNode<T>) node.getRight());
		}

		return result;
	}
}
