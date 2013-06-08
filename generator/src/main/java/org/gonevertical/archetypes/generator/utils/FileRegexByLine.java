package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;

public class FileRegexByLine extends DirectoryWalker<File> {

  private String name;
  private String regexFind;
  private String regexReplace;

  public FileRegexByLine(String name, String regexFind, String regexReplace) {
    super();
    
    this.name = name;
    this.regexFind = regexFind;
    this.regexReplace = regexReplace;
  }

  public List<File> start(File startDirectory) {
    List<File> results = new ArrayList<File>();
    try {
      walk(startDirectory, results);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return results;
  }
  
  protected void handleFile(File file, int depth, Collection<File> results) {
    if (file.getName().matches(".*" + name + "$")) {
      FileUtils.replaceInFileByLine(file, regexFind, regexReplace);
      results.add(file);
      System.out.println("Regexed file" + file.toString());
    }
  }
  
}
