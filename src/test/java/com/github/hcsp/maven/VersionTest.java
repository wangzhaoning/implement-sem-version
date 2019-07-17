package com.github.hcsp.maven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class VersionTest {
    @ParameterizedTest
    @ValueSource(strings = {"a.b.c", "a.0.0", "+1.+2.+3", "-1", "+1"})
    public void throwExceptionOnIllegalArguments(String version) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Version.of(version));
    }

    @ParameterizedTest
    @CsvSource({
        "1.0,   1",
        "1,     1.0.0",
        "1.0,   1.0.0",
        "1,     1.0.0",
        "1.2,   1.2.0",
        "1.2.1, 1.2.1",
    })
    public void supportShortVersion(String a, String b) {
        Assertions.assertEquals(Version.of(a), Version.of(b));
        Assertions.assertEquals(0, Version.of(a).compareTo(Version.of(b)));
        Assertions.assertEquals(0, Version.of(b).compareTo(Version.of(a)));
    }

    @ParameterizedTest
    @CsvSource({
        "1.0,   1.1",
        "1.1,   1.1.1",
        "1.2,   1.10",
        "1.2.10,   1.10",
        "0.0,   0.0.1",
        "0.19,  0.101",
    })
    public void compareTest(String a, String b) {
        Assertions.assertTrue(Version.of(a).compareTo(Version.of(b)) < 0);
        Assertions.assertTrue(Version.of(b).compareTo(Version.of(a)) > 0);
    }

    @ParameterizedTest
    @CsvSource({
        "1.0,   1, 0, 0",
        "1.1,   1, 1, 0",
        "1.2,   1, 2, 0",
        "0.0,   0, 0, 0",
        "0.99,  0, 99,0",
    })
    public void majorMinorPatchVersionTest(String version, int major, int minor, int patch) {
        Version semVersion = Version.of(version);
        Assertions.assertEquals(major, semVersion.getMajorVersion());
        Assertions.assertEquals(minor, semVersion.getMinorVersion());
        Assertions.assertEquals(patch, semVersion.getPatchVersion());
    }
}
