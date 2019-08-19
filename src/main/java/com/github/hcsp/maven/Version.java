package com.github.hcsp.maven;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String v1 = formatVersion(version1);
        String v2 = formatVersion(version2);
        String[] arr1 = v1.split("\\.");
        String[] arr2 = v2.split("\\.");
        int[] arrResult = new int[3];
        arrResult[0] = getResult(Integer.parseInt(arr1[0]), Integer.parseInt(arr2[0]));
        arrResult[1] = getResult(Integer.parseInt(arr1[1]), Integer.parseInt(arr2[1]));
        arrResult[2] = getResult(Integer.parseInt(arr1[2]), Integer.parseInt(arr2[2]));
        if (arrResult[0] != 0) {
            return arrResult[0];
        } else if (arrResult[1] != 0) {
            return arrResult[1];
        } else if (arrResult[2] != 0) {
            return arrResult[2];
        } else {
            return 0;
        }
    }

    public static String formatVersion(String version) {
        int count = 0;
        Pattern tempString = Pattern.compile("\\.");
        Matcher m = tempString.matcher(version);
        while (m.find()) {
            count++;
        }
        System.out.println(tempString);
        if (count == 1) {
            return version + ".0";
        } else if (count == 2) {
            return version;
        } else {
            return version + ".0.0";
        }
    }

    private static int getResult(int num1, int num2) {
        return Integer.compare(num1, num2);
    }

    public static void main(String[] args) {
        compare("0.19.0", "0.101.0");
    }
}
