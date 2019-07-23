package com.github.hcsp.maven;

import java.util.Arrays;

public class Version {
    /**
     * 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，比较两个"语义化版本"
     *
     * <p>传入两个形如x.y.z的字符串，比较两个语义化版本的大小。如果version1小于version2，返回-1；如果version1大于
     * version2，返回1。如果二者相等，返回0。
     *
     * <p>注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * @param version1 传入的版本字符串1，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @param version2 传入的版本字符串2，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @return -1/0/1 当version1 小于/等于/大于 version2时
     */
    public static int compare(String version1, String version2) {
        int[] version1Arr = toIntArray(version1);
        int[] version2Arr = toIntArray(version2);
        int maxLength = Math.max(version1Arr.length, version2Arr.length);
        for (int i = 0; i < maxLength; i++) {
            int item1 = getVersionItem(version1Arr, i);
            int item2 = getVersionItem(version2Arr, i);
            if (item1 == item2) {
                continue;
            }
            return item1 < item2 ? -1 : 1;
        }
        return 0;
    }

    private static int[] toIntArray(String version) {
        return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }

    private static int getVersionItem(int[] array, int index) {
        if (index > array.length - 1) {
            return 0;
        }
        return array[index];

    }
}
