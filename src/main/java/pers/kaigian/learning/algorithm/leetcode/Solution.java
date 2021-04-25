package pers.kaigian.learning.algorithm.leetcode;

import java.util.*;

/**
 * @author hukaiyang
 * @date 2021-04-21 14:22
 **/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class Solution {
	private void help(TreeNode root) {
		if (root.left == null && root.right == null) {
			return;
		}
		if (root.left != null) {
			TreeNode node = root.left;
			while (node.right != null) {
				node = node.right;
			}
			help(root.left);
			root.left = null;
			node.right = root;
		}
		if (root.right != null) {
			TreeNode node = root.right;
			while (node.left != null) {
				node = node.left;
			}
			help(root.right);
			root.right = node;
			node.left = null;
		}
	}

	public TreeNode increasingBST(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode head = root;
		while (head.left != null) {
			head = head.left;
		}
		help(root);
		return head;
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = {1, 2, 3};
		int[][] matrix = {
				{5, -4, -3, 4},
				{-3, -4, 4, 5},
				{5, 1, 5, -4}};
		System.out.println(solution);
	}
}
