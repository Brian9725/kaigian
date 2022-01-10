package pers.kaigian.learning.algorithm;

/**
 * 包含自己常用算法的工具类
 *
 * @author BrianHu
 * @create 2021-08-25 10:20
 **/
public class KaiGianUtils {
    /**
     * 在nums数组中二分查找target值
     *
     * @param nums   目标数组
     * @param target 目标值
     * @return
     */
    public static int binSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans = mid;
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 二分搜索nums数组中第一个大于或等于target的元素下标
     *
     * @param nums   目标数组
     * @param target 目标值
     * @param lower  标识是否可以等于
     * @return 返回下标
     */
    public static int binSearchLarger(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
