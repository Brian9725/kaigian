package pers.kaigian.learning.algorithm.leetcode;

import java.util.Stack;

/**
 * @Author BrianHu
 * @Create 2021-08-15 11:15
 **/
public class CQueue {
    private Stack<Integer> leftStack;
    private Stack<Integer> rightStack;

    public CQueue() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public void appendTail(int value) {
        if (rightStack.empty()) {
            while (!leftStack.empty()) {
                rightStack.push(leftStack.pop());
            }
        }
        rightStack.push(value);
    }

    public int deleteHead() {
        if (leftStack.empty() && rightStack.empty()) {
            return -1;
        }
        if (leftStack.empty()) {
            while (!rightStack.empty()) {
                leftStack.push(rightStack.pop());
            }
        }
        return leftStack.pop();
    }
}
