package document.project.java.ds_tree;

@SuppressWarnings("unchecked")
public class SearchTree <K extends Comparable, T> extends BinaryTree<K, T> {

    public SearchTree(){}

    protected SearchTree(Node root) {
        super(root);
    }

    @Override
    public BinaryTree<K, T> subTree(K key) {
        Node root = search(key, getRoot());
        return new SearchTree(root);
    }

    @Override
    public void remove(K key) {
        remove(getRoot(), key);
        decrease();
    }

    @Override
    public void add(K key, T element) {
        if (isEmpty()){
            setRoot(new Node(key, element));
        }else {
            add(getRoot(), key, element);
        }
        increase();
    }

    //private

    private Node add (Node node, K key, T element){
        if (node != null){
            if (key.compareTo(node.getKey()) < 0){
                node.setLeft(add(node.getLeft(), key, element));
            }
            else if (key.compareTo(node.getKey()) > 0){
                node.setRight(add(node.getRight(), key, element));
            }
            return node;
        }
        return new Node(key, element);
    }

    private Node remove(Node node, K key){
        if (node != null){
            if (key.equals(node.getKey())){
                return deleteNode(node);
            }
            if (key.compareTo(node.getKey()) < 0){
                node.setLeft(remove(node.getLeft(), key));
            }
            else if (key.compareTo(node.getKey()) > 0) {
                node.setRight(remove(node.getRight(), key));
            }
            return node;
        }
        return null;
    }

    private Node deleteNode (Node node){
        if (node.isLeaf()){
            return null;
        }
        if (node.getLeft() == null){
            return node.getRight();
        }
        if (node.getRight() == null){
            return node.getLeft();
        }
        Node smallest = smallestKey(node.getRight());
        node.setKey(smallest.getKey());
        node.setElement(smallest.getElement());
        remove(node.getRight(), smallest.getKey());
        return node;
    }

    private Node smallestKey (Node node){
        return node.getLeft() == null ? node : smallestKey(node.getLeft());
    }

    @Override
    protected Node search (K key, Node node){
        if (node != null) {
            int navigate = key.compareTo(node.getKey());
            if (navigate == 0) {
                return node;
            }
            if (navigate > 0) {
                return search(key, node.getRight());
            }
            return search(key, node.getLeft());
        }
        return null;
    }

}
