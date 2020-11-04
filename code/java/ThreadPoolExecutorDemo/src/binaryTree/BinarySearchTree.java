package binaryTree;

import binaryTree.TraversalTree.TreeNode;

import java.util.Scanner;

/**
 * @title: BinarySearchTree
 * @Author wenzhenzhen
 * @Date: 2020/10/16 3:02 下午
 */
public class BinarySearchTree {

    TreeNode predecessor(TreeNode root, int num) {
        TreeNode max = null;
        while (root != null) {
            if ((int) root.data < num) {
                max = root;
                root = root.rightNode;
            } else root = root.leftNode;
        }
        return max;
    }


    TreeNode root;

    public TreeNode createTree() {
        Scanner scan = new Scanner(System.in);

        Integer number = scan.nextInt();
        if (number == 0) {
            return null;
        }
        TreeNode node = new TreeNode(number);

        node.setLeftNode(createTree());
        node.setRightNode(createTree());

        root = node;
        return node;
    }

    public static TreeNode findTreeNotLessThan(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        TreeNode iter = root;
        if (iter.data < value) {
            return findTreeNotLessThan(iter.rightNode, value);
        } else if (iter.data == value) {
            return iter;
        } else if (iter.data > value) {
            if (iter.leftNode != null) {
                int leftTreeMax = maxNode(iter.leftNode);
                if (leftTreeMax >= value) {
                    return findTreeNotLessThan(iter.leftNode, value);
                } else {
                    return iter;
                }
            } else {
                return iter;
            }
        }
        return null;
    }

    public  static int  maxNode(TreeNode root) {
        TreeNode iter = root;
        if (iter != null) {
            while (iter != null && iter.rightNode != null) {
                iter = iter.rightNode;
            }
            return iter.data;
        } else {
            return 0;
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot() {
        this.root = createTree();
    }

    public static TreeNode findTreeNotGreaterThan(TreeNode root, Integer target) {
        if (root == null) {
            return null;
        }
        if (root.data == target) {
            return root;
        }
        if (root.data > target) {
            return findTreeNotGreaterThan(root.leftNode, target);
        }
        if (root.data < target) {
            if (root.rightNode != null) {
                int rightMinNode = minNode(root.rightNode);
                if (rightMinNode < target) {
                    return findTreeNotGreaterThan(root.rightNode, target);
                } else {
                    return root;
                }
            }
        }
        return null;
    }

    public static int minNode(TreeNode root) {
        TreeNode iter = root;
        if (iter != null) {
            while (iter != null && iter.leftNode != null) {
                iter = iter.leftNode;
            }
            return iter.data;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int shuzu []= {3,1,2,5,0,7,9,8};
        TreeNode tree =null;
        //先遍历数组创建树，以3为根结点
        for( int i = 0 ; i < shuzu.length ; i ++){
            tree=sortBinaryTree(tree, shuzu[i]);
        }
        int value = 4;
        System.out.println( + value + "  >=  " + findTreeNotGreaterThan(tree, value).data);
    }

    //建树，创建二叉查找树（左子节点<父节点<右子节点）
    public static TreeNode sortBinaryTree(TreeNode node, int i) {
        if (node == null) {
            node = new TreeNode(i);
            return node;
        } else {
            if (i <= node.data) {
                node.leftNode = sortBinaryTree(node.leftNode, i);
            } else {
                node.rightNode = sortBinaryTree(node.rightNode, i);
            }
            return node;
        }
    }
}
