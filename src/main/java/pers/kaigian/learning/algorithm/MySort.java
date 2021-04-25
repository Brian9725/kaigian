package pers.kaigian.learning.algorithm;

import java.util.Random;

/**
 * @author hukaiyang
 * @date 2021-04-21 14:20
 **/
//可以用leetcode912来测试结果
public class MySort {

	private void realMergeSort(int[] nums, int[] helper, int start, int end) {
		if (start >= end) {
			return;
		}
		int mid = start + ((end - start) >> 1);
		realMergeSort(nums, helper, start, mid);
		realMergeSort(nums, helper, mid + 1, end);
		int left = start, right = mid + 1, idx = start;
		while (left <= mid && right <= end) {
			if (nums[left] <= nums[right]) {
				helper[idx++] = nums[left++];
			} else {
				helper[idx++] = nums[right++];
			}
		}
		while (left <= mid) {
			helper[idx++] = nums[left++];
		}
		while (right <= end) {
			helper[idx++] = nums[right++];
		}
		for (int i = start; i <= end; i++) {
			nums[i] = helper[i];
		}
	}

	private void mergeSort(int[] nums, int start, int end) {
		int[] helper = new int[nums.length];
		realMergeSort(nums, helper, start, end);
	}

	private void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int luckyIdx = new Random().nextInt(end - start) + start;
		int lucky = nums[luckyIdx], idx = start;
		swap(nums, luckyIdx, end);
		for (int i = start; i <= end; i++) {
			if (nums[i] <= lucky) {
				swap(nums, i, idx++);
			}
		}
		quickSort(nums, start, idx - 2);
		quickSort(nums, idx, end);
	}

	private void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	private void adaptHeap(int[] nums, int idx, int len) {
		int cur = idx;
		while ((cur << 1) + 1 < len || (cur << 1) + 2 < len) {
			int leftIdx = (cur << 1) + 1, rightIdx = (cur << 1) + 2;
			int flag = 0, max = nums[cur];
			if (leftIdx < len && nums[leftIdx] > max) {
				flag = -1;
				max = nums[leftIdx];
			}
			if (rightIdx < len && nums[rightIdx] > max) {
				flag = 1;
			}
			if (flag == -1) {
				swap(nums, leftIdx, cur);
				cur = leftIdx;
			} else if (flag == 1) {
				swap(nums, rightIdx, cur);
				cur = rightIdx;
			} else {
				break;
			}
		}
	}

	private void buildHeap(int[] nums) {
		int len = nums.length;
		for (int i = (len >> 1) - 1; i >= 0; i--) {
			adaptHeap(nums, i, len);
		}
	}

	// 这里start必须从0开始，不然下标关系错误
	private void heapSort(int[] nums, int start, int end) {
		buildHeap(nums);
		for (int i = end; i >= start; i--) {
			swap(nums, 0, i);
			adaptHeap(nums, 0, i);
		}
	}

	public int[] sortArray(int[] nums) {
		heapSort(nums, 0, nums.length - 1);
		return nums;
	}

	public static void main(String[] args) {
		int[] nums = {5, 1, 3, 2};
		new MySort().sortArray(nums);
	}
}
