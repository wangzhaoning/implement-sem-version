package com.github.hcsp.maven;

public class Main {
    public static void main(String[] args) {
        Version v = Version.of("1.0");
        System.out.println(v.getMajorVersion()+"."+v.getMinorVersion()+"."+v.getPatchVersion());
    }
}
