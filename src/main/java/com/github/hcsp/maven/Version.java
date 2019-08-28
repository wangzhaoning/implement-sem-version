package com.github.hcsp.maven;

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
        String[] version1StringList = version1.split("\\.");
        String[] version2StringList = version2.split("\\.");
        String[] longerList, shorterList;
        int longerListFlag = 1;
        if (version1StringList.length > version2StringList.length) {
            longerList = version1StringList;
            shorterList = version2StringList;
        } else {
            longerListFlag = 2;
            longerList = version2StringList;
            shorterList = version1StringList;
        }
        for (int i = 0; i < shorterList.length; i++) {
            int item1 = Integer.parseInt(version1StringList[i]);
            int item2 = Integer.parseInt(version2StringList[i]);
            if (item1 < item2) {
                return -1;
            } else if (item1 > item2) {
                return 1;
            }
        }
        if (shorterList.length == longerList.length) {
            return 0;
        }
        for (int i = shorterList.length; i < longerList.length; i++) {
            int item = Integer.parseInt(longerList[i]);
            if (item != 0) {
                if (longerListFlag == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        compare("1.0", "1");
    }
}
