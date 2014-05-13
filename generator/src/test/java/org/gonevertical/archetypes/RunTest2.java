package org.gonevertical.archetypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RunTest2 {
  
  public static void main(String[] args) {
    new RunTest2().run();
  }

  private void run() {
    
    String directory = "/Users/branflake2267/git/Archetypes/archetypes/gxt-basic-3x";
   
    
    try {
      runCommand(directory, "/usr/local/bin/mvn", "package");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void runCommand(String directory, String... command) throws Exception {
    ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File(directory));

    try {
      Process p = pb.start();
      String s;
      BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((s = stdout.readLine()) != null) {
        System.out.println(s);
      }
      System.out.println("Exit value: " + p.waitFor());
      p.getInputStream().close();
      p.getOutputStream().close();
      p.getErrorStream().close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    } catch (InterruptedException e) {
      throw new Exception(e.getMessage());
    }
  }
  
}
