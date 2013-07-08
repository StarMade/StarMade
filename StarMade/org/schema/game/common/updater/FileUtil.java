package org.schema.game.common.updater;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtil
{
  public static void a(File paramFile1, File paramFile2)
  {
    System.err.println("DIR FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
    if (paramFile1.isDirectory())
    {
      if (!paramFile2.exists()) {
        paramFile2.mkdir();
      }
      String[] arrayOfString = paramFile1.list();
      for (int i = 0; i < arrayOfString.length; i++) {
        a(new File(paramFile1, arrayOfString[i]), new File(paramFile2, arrayOfString[i]));
      }
      return;
    }
    b(paramFile1, paramFile2);
  }
  
  public static void b(File paramFile1, File paramFile2)
  {
    System.err.println("FILE FROM " + paramFile1.getAbsolutePath() + " to " + paramFile2.getAbsolutePath());
    paramFile1 = new BufferedInputStream(new FileInputStream(paramFile1));
    paramFile2 = new BufferedOutputStream(new FileOutputStream(paramFile2));
    byte[] arrayOfByte = new byte[1024];
    int i;
    while ((i = paramFile1.read(arrayOfByte)) > 0) {
      paramFile2.write(arrayOfByte, 0, i);
    }
    paramFile2.flush();
    paramFile1.close();
    paramFile2.close();
  }
  
  public static final void a1(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte[1024];
    int i;
    while ((i = paramInputStream.read(arrayOfByte)) >= 0) {
      paramOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream.close();
    paramOutputStream.close();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    try
    {
      a3(new File("./sector-export/planet.smsec"), "./sector-export/mm/");
      a2(new File("./sector-export/mm/"));
      System.err.println(new File("./sector-export/mm/").delete());
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public static void a2(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile;
      int i = (arrayOfFile = paramFile.listFiles()).length;
      for (int j = 0; j < i; j++) {
        a2(arrayOfFile[j]);
      }
    }
    if (!paramFile.delete()) {
      System.err.println("Failed to del: " + paramFile.getAbsolutePath());
    }
  }
  
  public static void a3(File paramFile, String paramString)
  {
    if (!paramString.endsWith("/")) {
      paramString = paramString + "/";
    }
    (paramFile = new ZipFile(paramFile)).entries();
    new File(paramString).mkdirs();
    Enumeration localEnumeration = paramFile.entries();
    File localFile;
    (localFile = new File(paramString)).mkdirs();
    while (localEnumeration.hasMoreElements())
    {
      Object localObject1;
      if ((localObject1 = (ZipEntry)localEnumeration.nextElement()).isDirectory())
      {
        new File(paramString + ((ZipEntry)localObject1).getName()).mkdir();
      }
      else
      {
        int i;
        if ((i = (localObject2 = new String(((ZipEntry)localObject1).getName())).lastIndexOf("/")) >= 0)
        {
          localObject2 = ((String)localObject2).substring(0, i);
          if (!(localObject3 = new File(paramString + (String)localObject2)).exists()) {
            ((File)localObject3).mkdirs();
          }
        }
        Object localObject2 = new File(paramString + ((ZipEntry)localObject1).getName());
        System.err.println("Extracting file: " + ((ZipEntry)localObject1).getName() + " exists: " + localFile.exists() + ", is Dir: " + localFile.isDirectory() + ". " + localFile.getAbsolutePath());
        Object localObject3 = new BufferedInputStream(paramFile.getInputStream((ZipEntry)localObject1));
        localObject1 = new BufferedOutputStream(new FileOutputStream((File)localObject2));
        a1((InputStream)localObject3, (OutputStream)localObject1);
      }
    }
    paramFile.close();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.updater.FileUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */