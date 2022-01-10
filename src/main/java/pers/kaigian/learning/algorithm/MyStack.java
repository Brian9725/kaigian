package pers.kaigian.learning.algorithm;

/**
 * @author BrianHu
 * @create 2021-04-25 10:40
 **/
public class MyStack<T> {

    class Node {
        T val;
        Node next;

        Node() {
        }
    }

    private Node base;

    private Node top;

    private int size;

    public MyStack() {
        base = new Node();
        top = base;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (top == base);
    }

    public void push(T val) {
        Node node = new Node();
        node.val = val;
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        Node ans = top;
        top = top.next;
        size--;
        return ans.val;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.val;
    }

    public static void main(String[] args) {
        MyStack<Integer> integerMyStack = new MyStack<>();
        System.out.println(integerMyStack.getSize());
        System.out.println(integerMyStack.isEmpty());
        System.out.println(integerMyStack.peek());
        for (int i = 0; i < 5; i++) {
            integerMyStack.push(i);
        }
        System.out.println(integerMyStack.pop());
        System.out.println(integerMyStack.pop());
        System.out.println(integerMyStack.getSize());
    }
}
