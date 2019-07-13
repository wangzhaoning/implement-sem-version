package com.github.hcsp.maven;

public class Version implements Comparable<Version> {
    /** 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，实现一个可以进行比较的"语义化版本" */
    private Version() {}

    /**
     * 传入一个形如x.y.z的字符串，返回一个语义化版本的对象实例。 注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * <p>如果x.y.z任何一位包含非数字字符，则抛出IllegalArgumentException。
     *
     * @param version 传入的版本字符串，支持x/x.y/x.y.z，不支持x.y.z.a
     * @return 一个语义化版本对象实例，可与别的语义化版本对象实例进行比较
     */
    public static Version of(String version) {}

    /** @return 该版本的主版本号 */
    public int getMajorVersion() {}

    /** @return 该版本的次版本号 */
    public int getMinorVersion() {}

    /** @return 该版本的修订版本号 */
    public int getPatchVersion() {}
}
