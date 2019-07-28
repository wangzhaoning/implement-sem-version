package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static final int VERSION_LENGTH = 3;
    public static int compare(String version1, String version2) {
        List<String> v1 = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        List<String> v2 = new ArrayList<>(Arrays.asList(version2.split("\\.")));
        toStandardFormat(v1);
        toStandardFormat(v2);
        for (int i = 0; i < VERSION_LENGTH; i++) {
            if (Integer.parseInt(v1.get(i)) < Integer.parseInt(v2.get(i))){
                return -1;
            } else if (Integer.parseInt(v1.get(i)) > Integer.parseInt(v2.get(i))){
                return 1;
            } else {
                continue;
            }
        }
        return 0;
    }
    public static void toStandardFormat(List<String> original){
        int original_size = original.size();
        for (int i = original_size; i < VERSION_LENGTH; i++) {
            original.add("0");
        }
    }
}
