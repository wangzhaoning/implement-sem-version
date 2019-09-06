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
        SemVersion v1 = new SemVersion(version1);
        SemVersion v2 = new SemVersion(version2);
        return v1.compareTo(v2);
    }

    public static void main(String[] args) {
        compare("1", "1.0");
    }

    static class SemVersion implements Comparable {

        private static final String SEPARATOR = "\\.";

        private int majorVersion;
        private int minorVersion;
        private int lastVersion;

        SemVersion(String fullVersionName) {
            String formatVersion = format(fullVersionName);
            String[] numbers = formatVersion.split(SEPARATOR);

            this.majorVersion = Integer.parseInt(numbers[0]);
            this.minorVersion = Integer.parseInt(numbers[1]);
            this.lastVersion = Integer.parseInt(numbers[2]);
        }

        private String format(String fullVersionName) {
            if (numberOfSeparator(fullVersionName) == 0) {
                fullVersionName += ".0.0";
            } else if (numberOfSeparator(fullVersionName) == 1) {
                fullVersionName += ".0";
            }
            return fullVersionName;
        }

        private int numberOfSeparator(String fullVersionName) {
            return fullVersionName.split(SEPARATOR).length - 1;
        }

        @Override
        public int compareTo(Object o) {
            SemVersion other = (SemVersion) o;
            if (majorVersion != other.majorVersion) {
                return majorVersion - other.majorVersion;
            } else if (minorVersion != other.minorVersion) {
                return minorVersion - other.minorVersion;
            } else {
                return lastVersion - other.lastVersion;
            }
        }
    }
}
