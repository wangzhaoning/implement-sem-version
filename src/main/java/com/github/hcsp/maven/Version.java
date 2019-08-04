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
    return v2vc(version1).compareTo(v2vc(version2));
  }


  static VersionCompare v2vc(String version) {
    String[] strs = version.split("\\.");
    int len = strs.length;
    String x = len >= 1 ? strs[0] : "0";
    String y = len >= 2 ? strs[1] : "0";
    String z = len >= 3 ? strs[2] : "0";
    return new VersionCompare(x, y, z);

  }

  static class VersionCompare implements Comparable<VersionCompare> {
    private String x;
    private String y;
    private String z;

     VersionCompare(String x, String y, String z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    @Override
    public int compareTo(VersionCompare o) {
      if (Integer.parseInt(x) > Integer.parseInt(o.x)) {
        return 1;
      } else if (Integer.parseInt(x) < Integer.parseInt(o.x)) {
        return -1;
      } else if (Integer.parseInt(y) > Integer.parseInt(o.y)) {
        return 1;
      } else if (Integer.parseInt(y) < Integer.parseInt(o.y)) {
        return -1;
      } else if (Integer.parseInt(z) > Integer.parseInt(o.z)) {
        return 1;
      } else if (Integer.parseInt(z) < Integer.parseInt(o.z)) {
        return -1;
      }
      return 0;
    }
  }
}
