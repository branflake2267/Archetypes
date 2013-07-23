package org.gonevertical.archetypes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.DirectoryWalker;
import org.gonevertical.archetypes.generator.utils.FileUtils;

public class FileRegexCounting extends DirectoryWalker<File> {

  private String fileNameMatch;
  private String regexFind;
  private Map<String, Integer> found;

  public FileRegexCounting(String fileNameMatch, String regexFind) {
    super();
    this.fileNameMatch = fileNameMatch;
    this.regexFind = regexFind;
    found = new HashMap<String, Integer>();
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
    if (file.getName().matches(".*" + fileNameMatch + "$")) {
      findMatches(FileUtils.fileToString(file));
    }
  }
  
  public Map<String, Integer> getFound() {
    return found;
  }
  
  public void findMatches(String s) {
    try {
      Pattern p = Pattern.compile(regexFind);
      Matcher m = p.matcher(s);
      boolean found = m.find();
      if (!found) {
        return;
      }
      while(m.find()) {
        String g = m.group();
        add(g.toLowerCase());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void add(String key) {
    Integer v = found.get(key);
    if (v == null) { 
      found.put(key, 1);
    } else {
      found.put(key, v+1);
    }
  }
}
