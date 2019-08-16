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



        ArrayList<String> version1_arr = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        ArrayList<String> version2_arr = new ArrayList<>(Arrays.asList(version2.split("\\.")));

        int len = Math.max(version1_arr.size(), version2_arr.size());

        while (version1_arr.size()<len){
            version1_arr.add("0");
        }

        while (version2_arr.size()<len){
            version2_arr.add("0");
        }

        for (int i = 0; i < len; i++) {
            if (Integer.parseInt(version1_arr.get(i))<Integer.parseInt(version2_arr.get(i))){
                return -1;
            }

            if (Integer.parseInt(version1_arr.get(i))>Integer.parseInt(version2_arr.get(i))) {
                return 1;
            }
        }

        return 0;

    }


}
