package com.tgt.util;

import com.google.common.io.Resources;
import org.apache.commons.io.Charsets;

import java.io.IOException;
import java.net.URL;

public class TestUtils {

    public static String loadResourceAsString(String resourcePath) throws IOException {
        URL resource = Resources.getResource(resourcePath);
        return Resources.toString(resource, Charsets.UTF_8);
    }
}
