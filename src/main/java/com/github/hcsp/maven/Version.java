package com.github.hcsp.maven;

import java.util.ArrayList;
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
        ArrayList<String> versionArr1 = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        ArrayList<String> versionArr2 = new ArrayList<>(Arrays.asList(version2.split("\\.")));

        ArrayList<Integer> versionIntArr1 = new ArrayList<>();
        ArrayList<Integer> versionIntArr2 = new ArrayList<>();

        for (String v1 : versionArr1) {
            versionIntArr1.add(Integer.parseInt(v1));
        }
        for (String v2 : versionArr2) {
            versionIntArr2.add(Integer.parseInt(v2));
        }

        int length = Math.max(versionIntArr1.size(), versionIntArr2.size());

        while (versionIntArr1.size() < length) {
            versionIntArr1.add(0);
        }

        while (versionIntArr2.size()<length) {
            versionIntArr2.add(0);
        }

        for (int i = 0; i < length; i++) {
            if (versionIntArr1.get(i) < versionIntArr2.get(i)) {
                return -1;
            } else if (versionIntArr1.get(i) > versionIntArr2.get(i)) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
