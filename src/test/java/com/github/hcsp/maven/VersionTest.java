package com.github.hcsp.maven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class VersionTest {
    @ParameterizedTest
    @CsvSource({
        "1.0,   1",
        "1,     1.0.0",
        "1.0,   1.0.0",
        "1,     1.0.0",
        "1.2,   1.2.0",
        "1.2.1, 1.2.1",
    })
    public void supportShortVersion(String a, String b) throws Exception {
        Assertions.assertEquals(0, Version.compare(a, b));
        Assertions.assertEquals(0, Version.compare(b, a));
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
    public void compareTest(String a, String b) throws Exception {
        Assertions.assertTrue(Version.compare(a, b) < 0);
        Assertions.assertTrue(Version.compare(b, a) > 0);
    }
}
