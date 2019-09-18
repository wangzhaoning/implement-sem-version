package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
        if (version1.equals(version2)) {
            return 0;
        }
        List<Integer> v1 = new ArrayList<>();
        List<Integer> v2 = new ArrayList<>();
        StringTokenizer stv1 = new StringTokenizer(version1, ".");
        while (stv1.hasMoreTokens()) {
            v1.add(Integer.parseInt(stv1.nextToken()));
        }
        StringTokenizer stv2 = new StringTokenizer(version2, ".");
        while (stv2.hasMoreTokens()) {
            v2.add(Integer.parseInt(stv2.nextToken()));
        }
        convert(v1);
        convert(v2);
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i) > v2.get(i)) {
                return 1;
            } else if (v1.get(i) < v2.get(i)) {
                return -1;
            }
        }
        return 0;
    }

    private static void convert(List<Integer> v) {
        if (v.size() < 3) {
            v.add(0);
            convert(v);
        }
    }
}
