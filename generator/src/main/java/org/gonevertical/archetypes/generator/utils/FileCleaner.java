package org.gonevertical.archetypes.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;

public class FileCleaner extends DirectoryWalker<File> {

  private String name;

  public FileCleaner(String name) {
    super();
    
    this.name = name;
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

  @Override
  protected boolean handleDirectory(File directory, int depth, Collection<File> results) throws IOException {
    if (directory.getName().equals(name)) {
      directory.delete();
      results.add(directory);
      System.out.println("Deleted directory" + directory.toString());
    }
    return super.handleDirectory(directory, depth, results);
  }
  
  protected void handleFile(File file, int depth, Collection<File> results) {
    if (file.getName().matches(".*" + name + "$")) {
      file.delete();
      results.add(file);
      System.out.println("Deleted file" + file.toString());
    }
  }
  
}
