package binaryTree;

import java.util.*;

/**
 * @title: TraversalTree
 * @Author wenzhenzhen
 * @Date: 2020/9/23 7:06 下午
 * 遍历二叉树
 * https://juejin.im/post/6844903503807119374
 *
 */
public class TraversalTree {
    public static class TreeNode{
        public Integer data;
        public TreeNode leftNode;
        public TreeNode rightNode;
        public TreeNode(Integer val){
            data = val;
        }
        public void setData(Integer data) {
            this.data = data;
        }

        public void setLeftNode(TreeNode leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

    }
    // 二叉树先序遍历 非递归
    public void dfsPreOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur= root;
        while (!stack.empty()||cur!=null){
            while (cur!=null){
                System.out.println(cur.data);
                stack.push(cur);
                cur=cur.leftNode;
            }
            cur = stack.pop();
            cur=cur.rightNode;
        }
    }

    // 二叉树 中序遍历
    public void dfsInOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.empty()||cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur=cur.leftNode;
            }
            cur = stack.pop();
            System.out.println(cur);
            cur = cur.rightNode;
        }
    }

    // 二叉树后序遍历
    public void dfsPostOrder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null;
        while (!stack.isEmpty()||cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur=cur.leftNode;
            }
            cur = stack.peek();
            if (cur.rightNode==null||cur.rightNode==last){
                stack.pop();
                System.out.println(cur);
                last = cur;
                cur = null;
            }else {
                cur = cur.rightNode;
            }
        }
    }

    // 二叉树层序遍历
    public void bfd(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = root;
        if (temp!=null){
            queue.add(temp);
        }
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.leftNode!=null){
                queue.offer(temp.leftNode);
            }
            if (temp.rightNode!=null){
                queue.offer(temp.rightNode);
            }
        }
    }

    // 二叉树每一层的宽度
    public void getNumOfTreeLevel(TreeNode root){
        Queue<TreeNode> curQueue = new LinkedList();
        Queue<TreeNode> nextQueue;
        TreeNode cur = root;
        List<Integer> levelCount=new ArrayList<>();
        if (cur!=null){
            curQueue.offer(root);
        }
        while (!curQueue.isEmpty()){
            nextQueue=new LinkedList();
            levelCount.add(curQueue.size());
            while (!curQueue.isEmpty()){
                cur = curQueue.poll();
                if (cur.leftNode!=null){
                    nextQueue.offer(cur.leftNode);
                }
                if (cur.rightNode!=null){
                    nextQueue.offer(cur.rightNode);
                }
            }
            curQueue=nextQueue;
        }

    }

    /**
     * @Description 二叉树公共祖先
     * 递归方法
     * 时间复杂度：O(N)O(N)，其中 NN 是二叉树的节点数。二叉树的所有节点有且只会被访问一次，从 p 和 q 节点往上跳经过的祖先节点个数不会超过 NN，因此总的时间复杂度为 O(N)O(N)。
     *
     * 空间复杂度：O(N)O(N) ，其中 NN 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为 NN，因此空间复杂度为 O(N)O(N)，哈希表存储每个节点的父节点也需要 O(N)O(N) 的空间复杂度，因此最后总的空间复杂度为 O(N)O(N)。
     *
     */
    public TreeNode getPublicAncestorRecursive(TreeNode root,TreeNode sub1,TreeNode sub2){
        if (root==null){
            return null;
        }
        if (root==sub1||root==sub2){
            return root;
        }
        TreeNode left = getPublicAncestorRecursive(root.leftNode,sub1,sub2);
        TreeNode right = getPublicAncestorRecursive(root.rightNode,sub1,sub2);
        if (left!=null&&right!=null){
            return root;
        }
        else if (left!=null){
            // 两个节点都在左子树
            return left;
        }else {
            // 两个节点都在右子树
            return right;
        }
    }


    /**
     * @Description 非递归查找二叉树公共祖先
     */
    Map<TreeNode,TreeNode> parentMap = new HashMap<>();
    Set<TreeNode> visited = new HashSet<>();
    public TreeNode getPublicAncestor (TreeNode root,TreeNode sub1,TreeNode sub2){
        if (root ==null){
            return null;
        }
        dfs(root);
        while (sub1!=null){
            visited.add(parentMap.get(sub1));
            sub1 = parentMap.get(sub1);
        }
        while (sub2!=null){
            if (visited.contains(sub2)){
                return sub2;
            }
            sub2=parentMap.get(sub2);
        }
        return null;
    }
    public void dfs(TreeNode node){
        if (node==null){
            return;
        }
        if (node.leftNode!=null){
            parentMap.put(node.leftNode,node);
            dfs(node.leftNode);
        }
        if (node.rightNode!=null){
            parentMap.put(node.rightNode,node);
            dfs(node.rightNode);
        }
    }

}
