package com.niepeng.goldcode.jvm.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelperUtil {


  public static byte[] loadClassBytes(String classFilePath, String classPackageFile) throws IOException {
    String fileNamePath = classFilePath + classPackageFile.replaceAll("\\.", "/") + ".class";
    File f = new File(fileNamePath);
    if (!f.exists()) {
      throw new FileNotFoundException(fileNamePath);
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(f));
      int buf_size = 1024;
      byte[] buffer = new byte[buf_size];
      int len = 0;
      while (-1 != (len = in.read(buffer, 0, buf_size))) {
        bos.write(buffer, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      bos.close();
    }
  }

}