import cn.quguai.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("None,");
            return;
        }
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        return deserialize(new LinkedList<>(Arrays.asList(split)));
    }

    private TreeNode deserialize(LinkedList<String> split) {
        if (split.get(0).equals("None")) {
            split.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(split.get(0)));
        split.remove(0);
        root.left = deserialize(split);
        root.right = deserialize(split);
        return root;
    }

}