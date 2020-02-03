package document.project.java.ds_tree;

/**
 * An base class to every Binary Tree.
 * It's not a complete Tree, just basic methods common
 * to every Binary Tree, so it have to be extended to
 * adapt the methods to a complete Tree.
 *
 * @author Hilson Alexandre Wojcikiewicz Junior.
 *
 * @param <K> The type of the key to store an element.
 * @param <T> The type of the element to be stored.
 */
public abstract class BinaryTree <K, T> {

    private Node root;
    private int size = 0;

    public BinaryTree (){}

    protected BinaryTree (Node root){
        this.root = root;
        size = countSize(root);
    }

    //public

    public int size() {
        return size;
    }

    public boolean containsKey(K key){
        return get(key) != null;
    }

    public boolean isEmpty (){
        return root == null;
    }

    public T get(K key) {
        Node node = search(key, getRoot());
        if (node == null){
            return null;
        }
        return node.getElement();
    }

    public abstract void add(K key, T element);

    public abstract void remove(K key);

    public abstract BinaryTree<K, T> subTree (K key);

    public String inorder (){
        return internalInOrder(root);
    }

    public String preorder (){
        return internalPreOrder(root);
    }

    public String postOrder (){
        return internalPostOrder(root);
    }

    public String directory (){
        return levelPrint(1, root);
    }

    //protected

    protected Node getRoot (){
        return root;
    }

    protected void setRoot (Node root){
        this.root = root;
    }

    protected void increase(){
        size++;
    }

    protected void decrease(){
        size--;
    }

    protected abstract Node search(K key, Node node);

    //private

    private int countSize(Node node){
        if(node != null){
            return 1 + countSize(node.getRight()) + countSize(node.getLeft());
        }
        return 0;
    }

    private String levelPrint (int level, Node node){
        String string = "";
        String joiner = "";
        for (int i = 1; i < level; i++){
            joiner += "| ";
        }
        string += joiner + node.element + '\n';
        if (node.left != null) {
            string += levelPrint(level + 1, node.left);
        }
        if (node.right != null) {
            string += levelPrint(level + 1, node.right);
        }
        return string;
    }

    private String internalPreOrder (Node node){
        String toString = "";
        toString += node.element + " ";
        if (node.left != null){
            toString += internalPreOrder(node.left);
        }
        if (node.right != null){
            toString += internalPreOrder(node.right);
        }
        return toString;
    }

    private String internalInOrder (Node node){
        String toString = "";
        if (node.left != null){
            toString += internalInOrder(node.left);
        }
        toString += node.element + " ";
        if (node.right != null){
            toString += internalInOrder(node.right);
        }
        return toString;
    }

    private String internalPostOrder (Node node){
        String toString = "";
        if (node.left != null){
            toString += internalPostOrder(node.left);
        }
        if (node.right != null){
            toString += internalPostOrder(node.right);
        }
        toString += node.element + " ";
        return toString;
    }

    //Node

    protected class Node{

        private Node left;

        private Node right;

        private T element;

        private K key;

        protected Node (K key, T element){
            this.element = element;
            this.key = key;
        }

        protected void setElement (T element){
            this.element = element;
        }

        protected Node getLeft() {
            return left;
        }

        protected K getKey (){
            return key;
        }

        protected void setKey (K key){
            this.key = key;
        }

        protected void setLeft(Node left) {
            this.left = left;
        }

        protected Node getRight() {
            return right;
        }

        protected void setRight(Node right) {
            this.right = right;
        }

        protected T getElement() {
            return element;
        }

        protected boolean isLeaf (){
            return left == null && right == null;
        }

    }

}
