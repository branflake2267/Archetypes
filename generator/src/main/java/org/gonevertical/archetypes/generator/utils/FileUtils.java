package org.gonevertical.archetypes.generator.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

  public static void replaceInFileByLine(File file, String regexFind, String regexReplace) {
    if (file == null) {
      return;
    }
    String tmpName = file.getAbsolutePath() + ".tmp";
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    BufferedReader br = null;
    try {
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);
      br = new BufferedReader(new InputStreamReader(dis));
      FileWriter fstream = new FileWriter(tmpName);
      BufferedWriter out = new BufferedWriter(fstream);
      String s = null;
      while ((s = br.readLine()) != null) {
        s = s.replaceAll(regexFind, regexReplace);
        out.write(s + "\n");
      }
      out.close();
      fstream.close();
      br.close();
      fis.close();
      bis.close();
      dis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    File rf = new File(tmpName);
    rf.renameTo(file);
  }

  public static boolean deleteRecursive(File path) {
    if (path.exists()) {
      File[] files = path.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          deleteRecursive(files[i]);
        } else {
          boolean deleted = files[i].delete();
          System.out.println("deleted=" + deleted);
        }
      }
    }
    return (path.delete());
  }

  public static String findInFileAndReturnLine(File file, String regex) {
    if (file == null) {
      return null;
    }
    String line = null;
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;
    BufferedReader br = null;
    boolean found = false;
    try {
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);
      br = new BufferedReader(new InputStreamReader(dis));
      String s = null;
      while ((s = br.readLine()) != null) {
        found = StringUtils.findMatch(regex, s);
        if (found == true) {
          line = s;
          break;
        }
      }
      br.close();
      fis.close();
      bis.close();
      dis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return line;
  }

}
