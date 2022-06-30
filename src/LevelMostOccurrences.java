import java.util.ArrayDeque;
public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        // TODO: Add your code here...
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

    public static void createLevelQueue(BinNode<Integer> node, int remainingLevels, ArrayDeque<Integer> levelQueue){
        if(remainingLevels == 0) levelQueue.add(node.getData()); //if no remaining levels
        if (node.getRight() != null) createLevelQueue(node.getRight(), remainingLevels-1, levelQueue);
        if (node.getLeft() != null) createLevelQueue(node.getLeft(), remainingLevels -1, levelQueue);
    }
    //TODO: is it necessary???

    public static int levelOccurrence(BinNode<Integer> node, int num, int remainingLevels){
        int occurrences = 0;
        if (remainingLevels == 0){ //if no more remaining levels
            if (node.getData() == num) return 1;
            else return 0;
        }
        if (node.getRight() != null) occurrences += levelOccurrence(node.getRight(), num, remainingLevels -1);
        if (node.getLeft() != null) occurrences += levelOccurrence(node.getLeft(), num, remainingLevels - 1);
        return occurrences;
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
