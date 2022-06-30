import java.util.ArrayDeque;
public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        // TODO: Add your code here...
        int treeHeight = treeHeight(node);


        return 0;
    }


    public static <E> int treeHeight(BinNode<E> node) {
        if (node == null) return 0;
        int leftHeight = 0, rightHeight = 0;
        if (node.getLeft() != null) leftHeight = treeHeight(node.getLeft());
        if (node.getRight() != null) rightHeight = treeHeight(node.getRight());
        int max = (leftHeight > rightHeight ? leftHeight : rightHeight);
        return max + 1; //adding 1 for current level...
    }
}
