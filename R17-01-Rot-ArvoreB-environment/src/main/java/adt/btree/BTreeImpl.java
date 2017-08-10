package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

   protected BNode<T> root;
   protected int order;

   public BTreeImpl(int order) {
      this.order = order;
      this.root = new BNode<T>(order);
   }

   @Override
   public BNode<T> getRoot() {
      return this.root;
   }

   private void setRoot(BNode<T> node) {
      this.root = node;
   }

   @Override
   public boolean isEmpty() {
      return this.root.isEmpty();
   }

   @Override
   public int height() {
      int result = -1;
      if (!isEmpty()) {
         result += height(this.root);
      }
      return result;
   }

   private int height(BNode<T> node) {
      int result = 0;
      if (!node.isLeaf()) {
         result = 1 + height(node.getChildren().getFirst());
      }
      return result;
   }

   @SuppressWarnings("unchecked")
   @Override
   public BNode<T>[] depthLeftOrder() {
      BNode<T>[] array = new BNode[this.countNodes()];
      ;
      depthLeftOrderRecursive(array, 0, getRoot());
      return array;
   }

   private int depthLeftOrderRecursive(BNode<T>[] array, int i, BNode<T> node) {
      if (!node.isEmpty()) {
         array[i++] = node;
         for (int j = 0; j < node.getChildren().size(); j++) {
            i = depthLeftOrderRecursive(array, i, node.getChildren().get(j));
         }
      }
      return i;
   }

   @Override
   public int size() {
      return sizeRecursive(getRoot());
   }

   private int sizeRecursive(BNode<T> node) {
      int result = 0;
      if (!node.isEmpty()) {
         result = node.size();
         for (int i = 0; i < node.getChildren().size(); i++) {
            result += sizeRecursive(node.getChildren().get(i));
         }
      }
      return result;
   }

   @Override
   public BNodePosition<T> search(T element) {
      BNodePosition<T> result = new BNodePosition<T>();
      if (element != null) {
         result = searchRecursive(getRoot(), element);
      }
      return result;
   }

   private BNodePosition<T> searchRecursive(BNode<T> node, T element) {
      int i = 0;
      BNodePosition<T> result;
      while (i < node.getElements().size() && node.getElementAt(i).compareTo(element) < 0) {
         i++;
      }
      if (i < node.getElements().size() && node.getElementAt(i).compareTo(element) == 0) {
         result = new BNodePosition<T>(node, i);
      } else {
         if (node.isLeaf()) {
            result = new BNodePosition<T>();
         } else {
            result = searchRecursive(node.getChildren().get(i), element);
         }
      }
      return result;
   }

   private int countNodes() {
      return countNodesRecursive(getRoot());
   }

   private int countNodesRecursive(BNode<T> node) {
      int result = 0;
      if (!node.isEmpty()) {
         result = 1;
         for (int i = 0; i < node.getChildren().size(); i++) {
            result += countNodesRecursive(node.getChildren().get(i));
         }
      }

      return result;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         insertRecursive(getRoot(), element);
      }

   }

   private void insertRecursive(BNode<T> node, T element) {
      int i = 0;
      while (i < node.getElements().size() && node.getElementAt(i).compareTo(element) < 0) {
         i++;
      }
      if (emptyChildren(node)) {
         node.addElement(element);
         node.addChild(i, new BNode<T>(this.order));
         if (node.size() > node.getMaxKeys()) {
            split(node);
            resetRoot(node);
         }
      } else {
         insertRecursive(node.getChildren().get(i), element);
      }

   }

   private boolean emptyChildren(BNode<T> node) {
      boolean result = true;
      if (node.isLeaf()) {
         result = true;
      } else {
         int i = 0;
         while (i < node.getChildren().size()) {
            if (!node.getChildren().get(i).isEmpty()) {
               result = false;
               break;
            }
            i++;
         }
      }
      return result;
   }

   private void resetRoot(BNode<T> node) {
      while (node.getParent() != null) {
         node = node.getParent();
      }
      setRoot(node);
   }

   private void split(BNode<T> node) {
      node.split();
   }

   @SuppressWarnings("unused")
   private void promote(BNode<T> node) {
      node.promote();
   }

   // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
   @Override
   public BNode<T> maximum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public BNode<T> minimum(BNode<T> node) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

   @Override
   public void remove(T element) {
      // NAO PRECISA IMPLEMENTAR
      throw new UnsupportedOperationException("Not Implemented yet!");
   }

}
