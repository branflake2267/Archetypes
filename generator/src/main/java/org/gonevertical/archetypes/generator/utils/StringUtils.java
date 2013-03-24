package org.gonevertical.archetypes.generator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

  public static boolean findMatch(String regex, String s) {
    if (regex == null | s == null) {
      return false;
    }

    if (regex != null && regex.contains(")") == false) {
      System.err.println("oops!!! - you forgot to use parentheses to catch a group");
    }

    boolean found = false;
    try {
      Pattern p = Pattern.compile(regex);
      Matcher m = p.matcher(s);
      found = m.find();
    } catch (Exception e) {
      System.out.println("findMatch: regex error=");
      found = false;
      e.printStackTrace();
    }
    return found;
  }

}
