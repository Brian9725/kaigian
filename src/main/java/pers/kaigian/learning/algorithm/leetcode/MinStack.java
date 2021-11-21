package pers.kaigian.learning.algorithm.leetcode;

import java.util.Stack;

/**
 * @Author BrianHu
 * @Create 2021-08-15 11:22
 **/
public class MinStack {

	private Stack<Integer> valStack;
	private Stack<Integer> minValStack;

	/**
	 * initialize your data structure here.
	 */
	public MinStack() {
		valStack = new Stack<>();
		minValStack = new Stack<>();
	}

	public void push(int x) {
		valStack.push(x);
		if (minValStack.empty() || x <= minValStack.peek()) {
			minValStack.push(x);
		}
	}

	public void pop() {
		int val = valStack.pop();
		if (minValStack.peek() == val) {
			minValStack.peek();
		}
	}

	public int top() {
		return minValStack.peek();
	}

	public int min() {
		return minValStack.peek();
	}
}