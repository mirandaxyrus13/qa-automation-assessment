package com.test.automation.framework.core;

public class OSChecker {
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }
}
