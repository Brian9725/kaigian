package pers.kaigian.learning.algorithm.leetcode;

import java.util.HashMap;

/**
 * @author hukaiyang
 * @date 2021-06-04 14:04
 **/
public class LRUCache {
	class Node {
		int key;
		int val;
		Node pre;
		Node next;
	}

	private int capacity;
	private Node head;
	private Node tail;
	private HashMap<Integer, Node> hashMap;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		head = new Node();
		tail = new Node();
		hashMap = new HashMap<>();
		head.next = tail;
		tail.pre = head;
	}

	public int get(int key) {
		Node node = hashMap.get(key);
		if (node == null) {
			return -1;
		}
		node.pre.next = node.next;
		node.next.pre = node.pre;
		node.pre = tail.pre;
		tail.pre.next = node;
		node.next = tail;
		tail.pre = node;
		return node.val;
	}

	public void put(int key, int value) {
		if (hashMap.containsKey(key)) {
			Node node = hashMap.get(key);
			node.val = value;
			node.pre.next = node.next;
			node.next.pre = node.pre;
			node.pre = tail.pre;
			tail.pre.next = node;
			node.next = tail;
			tail.pre = node;
		} else {
			Node node = new Node();
			node.key = key;
			node.val = value;
			node.pre = tail.pre;
			tail.pre.next = node;
			node.next = tail;
			tail.pre = node;
			hashMap.put(key, node);
			if (hashMap.size() > capacity) {
				Node first = head.next;
				head.next = first.next;
				first.next.pre = head;
				hashMap.remove(first.key);
			}
		}
	}
}
