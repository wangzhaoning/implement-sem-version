package com.github.hcsp.maven;


import org.apache.commons.lang.StringUtils;

/**
 * @author Jinlf
 */
public class Version implements Comparable<Version> {
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    /** 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，实现一个可以进行比较的"语义化版本" */
    private Version(int majorVersion, int minorVersion, int patchVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
    }

    private static final int VERSION_LIMIT = 3;

    /**
     * 传入一个形如x.y.z的字符串，返回一个语义化版本的对象实例。 注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * <p>如果x.y.z任何一位包含非数字字符，则抛出IllegalArgumentException。
     *
     * @param version 传入的版本字符串，支持x/x.y/x.y.z，不支持x.y.z.a
     * @return 一个语义化版本对象实例，可与别的语义化版本对象实例进行比较
     */
    public static Version of(String version) throws IllegalArgumentException {

        //参数转换成字符串数组
        String[] strVersions = version.split("\\.");

        //初始化版本号
        int[] intVersions ={0,0,0};
        int lengthOfVersions = strVersions.length;

        //版本号超过3个数字 抛出异常
        if(lengthOfVersions>VERSION_LIMIT){
            throw new IllegalArgumentException();
        }

        //遍历每个版本号，如果为非数字，抛出异常，否则转换成数字保存到数组
        for (int i = 0; i < VERSION_LIMIT && i < lengthOfVersions; i++) {
            if(!StringUtils.isNumeric(strVersions[i])){
                throw new IllegalArgumentException();
            }else{
                intVersions[i] = Integer.parseInt(strVersions[i]);
            }
        }

        //构造一个包含三个数字的版本号
        Version v = new Version(intVersions[0],intVersions[1],intVersions[2]);
        return v;
    }

    /** @return 该版本的主版本号 */
    public int getMajorVersion() {
        return majorVersion;
    }

    /** @return 该版本的次版本号 */
    public int getMinorVersion() {
        return minorVersion;
    }

    /** @return 该版本的修订版本号 */
    public int getPatchVersion() {
        return patchVersion;
    }

    @Override
    public int compareTo(Version o) {
        if(this.majorVersion > o.majorVersion) {
            return 1;
        }
        if(this.majorVersion == o.getMajorVersion() && this.minorVersion > o.minorVersion){
            return 1;
        }
        if(this.majorVersion == o.getMajorVersion() && this.minorVersion == o.minorVersion && this.patchVersion > o.getPatchVersion()){
            return 1;
        }
        if(this.majorVersion == o.getMajorVersion() && this.minorVersion == o.minorVersion && this.patchVersion == o.getPatchVersion()){
            return 0;
        }
        return -1;
    }



    @Override
    public boolean equals(Object obj) {
        Version o = (Version)obj;
        return this.majorVersion == o.getMajorVersion() && this.minorVersion == o.minorVersion && this.patchVersion == o.getPatchVersion();
    }
}
