package pers.kaigian.learning.algorithm;

/**
 * @Author BrianHu
 * @Create 2021-04-25 10:40
 **/
// 可以用leetcode28测试
public class KMP {

	private int kmp(String haystack, String needle) {
		int lenH = haystack.length(), lenN = needle.length();
		int[] next = new int[lenN];
		for (int i = 1, j = 0; i < lenN; i++) {
			while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
				j = next[j - 1];
			}
			if (needle.charAt(i) == needle.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		int i = 0, j = 0;
		while (i < lenH && j < lenN) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
			} else {
				if (j == 0) {
					i++;
				} else {
					j = next[j - 1];
				}
			}
		}
		return (j == lenN) ? i - lenN : -1;
	}

	public int strStr(String haystack, String needle) {
		int lenH = haystack.length(), lenN = needle.length();
		if (lenH < lenN) {
			return -1;
		}
		if (lenH == lenN) {
			return haystack.equals(needle) ? 0 : -1;
		}
		if (lenN == 0) {
			return 0;
		}
		return kmp(haystack, needle);
	}
}
