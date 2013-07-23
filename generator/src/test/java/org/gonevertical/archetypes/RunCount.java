package org.gonevertical.archetypes;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class RunCount {

  /**
   * @param args
   */
  public static void main(String[] args) {
    new RunCount().run();
  }

  private void run() {
    File startDirectory = new File(
        "/Users/branflake2267/Documents/workspace_sencha/gxt/com.sencha.gxt.ui/src/main/resources/theme/__package__");

    String regexFind = "(#[a-zA-Z0-9]+)";
    String fileNameMatch = ".*";
    FileRegexCounting frc = new FileRegexCounting(fileNameMatch, regexFind);
    frc.start(startDirectory);
    
    Map<String, Integer> found = frc.getFound();
    
    Set<String> keys = found.keySet();
    
    for (String key : keys) {
      Integer v = found.get(key);
      System.out.println(key.replace("#", "") + ", " + v);
    }
  }

}
