package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FileUtils;

public class RegexRenameFiles extends DirectoryWalker<File> {

  private String regexFind;
  private String replace;
  private String regexMatchFile;

  public RegexRenameFiles(String regexMatchFile, String regexFind, String replace) {
    super();
    this.regexMatchFile = regexMatchFile;
    
    this.regexFind = regexFind;
    this.replace = replace;
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
  
  protected void handleFile(File oldFile, int depth, Collection<File> results) {
    if (oldFile.getName().matches(regexMatchFile)) {
      String newName = oldFile.getName().replaceAll(regexFind, replace);
      String newFilePath = oldFile.getAbsolutePath().replace(oldFile.getName(), "") + newName;
      File newFile = new File(newFilePath);
      try {
        FileUtils.moveFile(oldFile, newFile);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
}
