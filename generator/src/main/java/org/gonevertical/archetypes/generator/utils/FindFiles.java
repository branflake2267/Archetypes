package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;

public class FindFiles extends DirectoryWalker<File> {

  private String regexFind;

  public FindFiles(String regexFind) {
    super();

    this.regexFind = regexFind;
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
    if (FileUtils.findInFile(file, regexFind)) {
      results.add(file);
      System.out.println("Find files: file=" + file.toString());
    }
  }

}
