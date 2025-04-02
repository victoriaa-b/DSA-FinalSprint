package org.keyin.tree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Insert method
    public void insert(int value) {
        if (value < this.value) {
            if (left == null) {
                left = new TreeNode(value);
            } else {
                left.insert(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                right = new TreeNode(value);
            } else {
                right.insert(value);
            }
            // maybe add message about failure
        }
    }
}
