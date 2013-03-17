package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FileUtils;

public class MoveFile extends DirectoryWalker<File> {

  private String src;
  private String newName;

  public MoveFile(String src, String dest) {
    super();
    
    this.src = src;
    this.newName = dest;
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
    if (oldFile.getName().matches(src)) {
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
