import document.project.java.ds_tree.BinaryTree;
import document.project.java.ds_tree.SearchTree;

public class TreeTest {

    public static void main(String[] args) {
        BinaryTree<Integer, Character> bt = new SearchTree<>();
        bt.add(8, '+');
        bt.add(9, 'E');
        bt.add(10, 'H');
        bt.add(6, '*');
        bt.add(7, 'D');
        bt.add(2, '/');
        bt.add(4, '*');
        bt.add(5, 'C');
        bt.add(3, 'B');
        bt.add(1, 'A');

        System.out.println(bt.directory());
        System.out.println(bt.size());

        bt.remove(9);
        bt.remove(7);
        bt.remove(2);

        System.out.println(bt.directory());
        System.out.println(bt.size());

        bt = bt.subTree(3);
        System.out.println(bt.directory());
        System.out.println(bt.size());
    }

}
