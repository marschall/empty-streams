package com.github.marschall.emptystreams;

final class JdkCompatibility {

  private static final boolean JDK_8;

  static {
    boolean isJdk8;
    try {
      Class.forName("java.lang.Runtime$Version");
      isJdk8 = false;
    } catch (ClassNotFoundException e) {
      isJdk8 = true;
    }
    JDK_8 = isJdk8;
  }

  private JdkCompatibility() {
    throw new AssertionError("not instantiable");
  }

  static boolean isBuggyJdkStream(Object s) {
    if (!JDK_8) {
      return false;
    }
    return s.getClass().getPackage().getName().startsWith("java.");
  }

}
