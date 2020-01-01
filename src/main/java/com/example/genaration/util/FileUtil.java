package com.example.genaration.util;

import org.apache.commons.lang3.StringUtils;

public class FileUtil {
    public static boolean match(String fileName, String... exts) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        for (String ext1 : exts) {
            if (ext.equals(ext1) || ext.equals(ext1.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
