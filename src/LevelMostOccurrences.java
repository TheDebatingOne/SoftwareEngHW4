public class LevelMostOccurrences {
    /**
     * Finds the level of a binary tree with the most occurrences of a specific value
     * @param node The root of the tree
     * @param num The value to count occurrences of
     * @return The level with the most occurrences of num
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        int treeHeight = treeHeight(node);
        int occurrencesPerLevel;
        int maxOccurrences = 0;
        int maxLevel = -1;
        for (int level = 0; level < treeHeight; level++){
            occurrencesPerLevel = levelOccurrence(node, num, level);
            if (occurrencesPerLevel > maxOccurrences){
                maxOccurrences = occurrencesPerLevel;
                maxLevel = level;
            }
        }
        return maxLevel;
    }

    /**
     * Calculates the number of occurrences of a value in a specific level of a binary tree
     * @param node The root of the tree
     * @param num The value to count occurrences of
     * @param remainingLevels The level of the tree to check
     * @return The number of times num appears in the specified level of the tree
     */
    public static int levelOccurrence(BinNode<Integer> node, int num, int remainingLevels){
        int occurrences = 0;
        if (remainingLevels == 0) //if no more remaining levels
            return node.getData() == num? 1: 0;
        if (node.getRight() != null)
            occurrences += levelOccurrence(node.getRight(), num, remainingLevels -1);
        if (node.getLeft() != null)
            occurrences += levelOccurrence(node.getLeft(), num, remainingLevels - 1);
        return occurrences;
    }

    /**
     * Finds the height of a binary tree
     * @param node The root of the tree
     * @return The height of the tree
     * @param <E> The type of data in the nodes
     */
    public static <E> int treeHeight(BinNode<E> node) {
        if (node == null) return 0;
        int leftHeight = 0, rightHeight = 0;
        if (node.getLeft() != null) leftHeight = treeHeight(node.getLeft());
        if (node.getRight() != null) rightHeight = treeHeight(node.getRight());
        int max = (leftHeight > rightHeight ? leftHeight : rightHeight);
        return max + 1; //adding 1 for current level...
    }
}
