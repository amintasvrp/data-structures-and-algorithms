package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	@SuppressWarnings("unchecked")
	protected void insertWithNode(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).parent(node).build());
		} else {
			if ((element.compareTo(node.getData()) < 0)) {
				insertWithNode((BSTNode<T>) node.getLeft(), element);
			} else if ((element.compareTo(node.getData()) > 0)) {
				insertWithNode((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		removeRecursive(node);
		resetRoot(node);
	}

	private void resetRoot(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> nodeAux = node;
			while (nodeAux.getParent() != null) {
				nodeAux = (BSTNode<T>) nodeAux.getParent();
			}
			setRoot(nodeAux);
		}
	}

	@Override
	protected void removeRecursive(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);
			} else if (hasOneChild(node)) {
				if (!(node.equals(getRoot()) && node.getParent() == null)) {
					if (node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {

							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());

						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {

							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {

							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());

						}
					}
				} else {
					if (getRoot().getLeft().isEmpty()) {
						setRoot((BSTNode<T>) getRoot().getRight());
						getRoot().setParent(null);
					} else if (getRoot().getRight().isEmpty()) {
						setRoot((BSTNode<T>) getRoot().getLeft());
						getRoot().setParent(null);
					}
				}
				rebalanceUp(node);
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				removeRecursive(sucessor);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (node != null) {
			if (!node.isEmpty()) {
				result = getHeight((BSTNode<T>) node.getLeft()) - getHeight((BSTNode<T>) node.getRight());
			}
		}
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null) {
			if (calculateBalance(node) > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					adt.bt.Util.rightRotation(node);
				} else {
					adt.bt.Util.leftRotation((BSTNode<T>) node.getLeft());
					adt.bt.Util.rightRotation((BSTNode<T>) node);
				}
			} else if (calculateBalance(node) < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					adt.bt.Util.leftRotation(node);
				} else {
					adt.bt.Util.rightRotation((BSTNode<T>) node.getRight());
					adt.bt.Util.leftRotation((BSTNode<T>) node);
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null) {
				rebalance((BSTNode<T>) parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
	}
}
