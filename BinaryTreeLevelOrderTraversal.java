//Time Complexity: O(n)
//Space Complexity: O(n)
// Did this code successfully run on Leetcode :Yes
//Approach: This is a BFS implementation of the problem where we use a Queue to process all the nodes at a given level from left to right
//at each level, we create a List and put the elements in that level, put it in the output list
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> output = new ArrayList<>();
        if (root == null) return output;

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            final List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                final TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            output.add(level);
        }

        return output;
    }

    public static void main(String[] args) {
        final BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
        final TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        final List<List<Integer>> levelOrder = binaryTreeLevelOrderTraversal.levelOrder(root);

        assert levelOrder.getFirst().size() == 1;
        assert levelOrder.get(1).size() == 2;
        assert levelOrder.get(2).size() == 2;
    }
}
