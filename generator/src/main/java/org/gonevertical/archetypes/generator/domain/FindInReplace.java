package org.gonevertical.archetypes.generator.domain;

public class FindInReplace {

  private String findFiles;
  private String find;
  private String replace;
  
  public FindInReplace(String findFiles, String find, String replace) {
    this.findFiles = findFiles;
    this.find = find;
    this.replace = replace;
  }

  public String getFindFiles() {
    return findFiles;
  }
  
  public String getFind() {
    return find;
  }
  
  public String getReplace() {
    return replace;
  }
  
}
