

package com.github.hcsp.maven;

import java.util.Objects;
import java.util.regex.Pattern;

public class Version implements Comparable<Version> {
    /**
     * 正则表达式：验证版本号是否合法
     */
    private static final String REGEX_VERSION = "\\d+(\\.\\d+){0,2}";
    private String revisedVersion;

    private com.github.zafarkhaja.semver.Version innerVersion;
    /** 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，实现一个可以进行比较的"语义化版本" */
    private Version(String version) {
        if(isVerifyVersion(version)){
            revisedVersion = Version.getRevisedVersion(version);
            innerVersion = com.github.zafarkhaja.semver.Version.valueOf(revisedVersion);
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * 传入一个形如x.y.z的字符串，返回一个语义化版本的对象实例。 注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * <p>如果x.y.z任何一位包含非数字字符，则抛出 IllegalArgumentException。
     *
     * @param version 传入的版本字符串，支持x/x.y/x.y.z，不支持x.y.z.a
     * @return 一个语义化版本对象实例，可与别的语义化版本对象实例进行比较
     */
    public static Version of(String version) {
        return new Version(version);
    }

    /** @return 该版本的主版本号 */
    public int getMajorVersion() {
        return innerVersion.getMajorVersion();
    }

    /** @return 该版本的次版本号 */
    public int getMinorVersion() {
        return innerVersion.getMinorVersion();
    }

    /** @return 该版本的修订版本号 */
    public int getPatchVersion() {
        return innerVersion.getPatchVersion();
    }

    public com.github.zafarkhaja.semver.Version getInnerVersion() {
        return innerVersion;
    }

    @Override
    public int compareTo(Version other) {
        return innerVersion.compareTo(other.getInnerVersion());
    }

    private static boolean isVerifyVersion(String version) {
        return Pattern.matches(REGEX_VERSION, version);
    }

    private static String getRevisedVersion(String version){
        String [] versions = version.split("\\.");
        String result;

        switch (versions.length){
            case 1:{
                result = versions[0] + ".0.0";
                break;
            }
            case 2:{
                result = versions[0] + "." + versions[1] + ".0";
                break;
            }
            case 3:{
                result = version;
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return Objects.equals(revisedVersion, version.revisedVersion) &&
                Objects.equals(innerVersion, version.innerVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revisedVersion, innerVersion);
    }
}
