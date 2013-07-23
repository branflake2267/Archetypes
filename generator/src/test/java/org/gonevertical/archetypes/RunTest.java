package org.gonevertical.archetypes;

import java.io.File;

import org.gonevertical.archetypes.generator.utils.FileRegexByLine;
import org.gonevertical.archetypes.generator.utils.RegexRenameFiles;

public class RunTest {
  
  public static void main(String[] args) {
    new RunTest().run();
  }

  private void run() {
    renameFiles();
    renameInFiles();

    System.out.println("finished");
  }

  private void renameInFiles() {
    regexFindAndReplaceFiles(".*", "Blue", "${themeName}");
    
    regexFindAndReplaceFiles(".css", "background: #D6E3F2", "background: #D6E3F2 ${colorgBg}");
    regexFindAndReplaceFiles(".css", "#99bbe8", "${color1}");
    regexFindAndReplaceFiles(".css", "#99bbe8", "${color1}");
  }

  private void renameFiles() {
    File startDirectory = new File(
        "/Users/branflake2267/Documents/workspace_sencha/gxt/com.sencha.gxt.ui/src/main/resources/theme/__package__");
    RegexRenameFiles rename = new RegexRenameFiles("^Blue.*", "^Blue", "__themeName__");
    rename.start(startDirectory);
  }
  
  private void regexFindAndReplaceFiles(String name, String regexFind, String regexReplace) {
    String base = "/Users/branflake2267/Documents/workspace_sencha/gxt/com.sencha.gxt.ui/src/main/resources/theme/__package__";
    File startDir = new File(base);
    FileRegexByLine fr = new FileRegexByLine(name, regexFind, regexReplace);
    fr.start(startDir);
  }
  
}
