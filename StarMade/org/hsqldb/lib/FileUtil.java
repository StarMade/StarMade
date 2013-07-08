package org.hsqldb.lib;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Random;
import org.hsqldb.lib.java.JavaSystem;

public class FileUtil
  implements FileAccess
{
  private static FileUtil fileUtil = new FileUtil();
  private static FileAccessRes fileAccessRes = new FileAccessRes();
  public final boolean fsIsIgnoreCase = new File("A").equals(new File("a"));
  public final boolean fsNormalizesPosixSeparator = new File("/").getPath().endsWith(File.separator);
  final Random random = new Random(System.currentTimeMillis());
  
  public static FileUtil getFileUtil()
  {
    return fileUtil;
  }
  
  public static FileAccess getFileAccess(boolean paramBoolean)
  {
    return paramBoolean ? fileAccessRes : fileUtil;
  }
  
  public boolean isStreamElement(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public InputStream openInputStreamElement(String paramString)
    throws IOException
  {
    try
    {
      return new FileInputStream(new File(paramString));
    }
    catch (Throwable localThrowable)
    {
      throw JavaSystem.toIOException(localThrowable);
    }
  }
  
  public void createParentDirs(String paramString)
  {
    makeParentDirectories(new File(paramString));
  }
  
  public void removeElement(String paramString)
  {
    if (isStreamElement(paramString)) {
      delete(paramString);
    }
  }
  
  public void renameElement(String paramString1, String paramString2)
  {
    renameWithOverwrite(paramString1, paramString2);
  }
  
  public OutputStream openOutputStreamElement(String paramString)
    throws IOException
  {
    return new FileOutputStream(new File(paramString));
  }
  
  public boolean delete(String paramString)
  {
    return new File(paramString).delete();
  }
  
  public void deleteOnExit(File paramFile)
  {
    JavaSystem.deleteOnExit(paramFile);
  }
  
  public boolean exists(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public boolean exists(String paramString, boolean paramBoolean, Class paramClass)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return false;
    }
    return paramBoolean ? false : null != paramClass.getResource(paramString) ? true : getFileUtil().exists(paramString);
  }
  
  private boolean renameWithOverwrite(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    delete(paramString2);
    boolean bool = localFile.renameTo(new File(paramString2));
    if (bool) {
      return true;
    }
    System.gc();
    delete(paramString2);
    if (exists(paramString2)) {
      new File(paramString2).renameTo(new File(newDiscardFileName(paramString2)));
    }
    return localFile.renameTo(new File(paramString2));
  }
  
  public String absolutePath(String paramString)
  {
    return new File(paramString).getAbsolutePath();
  }
  
  public File canonicalFile(File paramFile)
    throws IOException
  {
    return new File(paramFile.getCanonicalPath());
  }
  
  public File canonicalFile(String paramString)
    throws IOException
  {
    return new File(new File(paramString).getCanonicalPath());
  }
  
  public String canonicalPath(File paramFile)
    throws IOException
  {
    return paramFile.getCanonicalPath();
  }
  
  public String canonicalPath(String paramString)
    throws IOException
  {
    return new File(paramString).getCanonicalPath();
  }
  
  public String canonicalOrAbsolutePath(String paramString)
  {
    try
    {
      return canonicalPath(paramString);
    }
    catch (Exception localException) {}
    return absolutePath(paramString);
  }
  
  public void makeParentDirectories(File paramFile)
  {
    String str = paramFile.getParent();
    if (str != null)
    {
      new File(str).mkdirs();
    }
    else
    {
      str = paramFile.getPath();
      int i = str.lastIndexOf('/');
      if (i > 0)
      {
        str = str.substring(0, i);
        new File(str).mkdirs();
      }
    }
  }
  
  public static String makeDirectories(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      localFile.mkdirs();
      return localFile.getCanonicalPath();
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  public FileAccess.FileSync getFileSync(OutputStream paramOutputStream)
    throws IOException
  {
    return new FileSync((FileOutputStream)paramOutputStream);
  }
  
  public static boolean deleteOrRenameDatabaseFiles(String paramString)
  {
    DatabaseFilenameFilter localDatabaseFilenameFilter = new DatabaseFilenameFilter(paramString);
    File[] arrayOfFile1 = localDatabaseFilenameFilter.getExistingFileListInDirectory();
    for (int i = 0; i < arrayOfFile1.length; i++) {
      arrayOfFile1[i].delete();
    }
    File localFile = new File(localDatabaseFilenameFilter.canonicalFile.getPath() + ".tmp");
    if (localFile.isDirectory())
    {
      File[] arrayOfFile2 = localFile.listFiles();
      for (int k = 0; k < arrayOfFile2.length; k++) {
        arrayOfFile2[k].delete();
      }
      localFile.delete();
    }
    arrayOfFile1 = localDatabaseFilenameFilter.getExistingMainFileSetList();
    if (arrayOfFile1.length == 0) {
      return true;
    }
    System.gc();
    for (int j = 0; j < arrayOfFile1.length; j++) {
      arrayOfFile1[j].delete();
    }
    arrayOfFile1 = localDatabaseFilenameFilter.getExistingMainFileSetList();
    for (j = 0; j < arrayOfFile1.length; j++) {
      arrayOfFile1[j].renameTo(new File(newDiscardFileName(arrayOfFile1[j].getPath())));
    }
    return true;
  }
  
  public static File[] getDatabaseFileList(String paramString)
  {
    DatabaseFilenameFilter localDatabaseFilenameFilter = new DatabaseFilenameFilter(paramString);
    return localDatabaseFilenameFilter.getExistingFileListInDirectory();
  }
  
  public static String newDiscardFileName(String paramString)
  {
    String str1 = StringUtil.toPaddedString(Integer.toHexString((int)System.currentTimeMillis()), 8, '0', true);
    String str2 = paramString + "." + str1 + ".old";
    return str2;
  }
  
  static class DatabaseFilenameFilter
    implements FilenameFilter
  {
    String[] suffixes = { ".backup", ".properties", ".script", ".data", ".log", ".lck", ".lobs", ".sql.log", ".app.log" };
    private String dbName;
    private File parent;
    private File canonicalFile;
    
    DatabaseFilenameFilter(String paramString)
    {
      this.dbName = paramString;
      this.canonicalFile = new File(paramString);
      try
      {
        this.canonicalFile = this.canonicalFile.getCanonicalFile();
      }
      catch (Exception localException) {}
      this.parent = this.canonicalFile.getParentFile();
    }
    
    public File[] getCompleteMainFileSetList()
    {
      File[] arrayOfFile = new File[this.suffixes.length];
      for (int i = 0; i < this.suffixes.length; i++) {
        arrayOfFile[i] = new File(this.canonicalFile.getPath() + this.suffixes[i]);
      }
      return arrayOfFile;
    }
    
    public File[] getExistingMainFileSetList()
    {
      File[] arrayOfFile = getCompleteMainFileSetList();
      HsqlArrayList localHsqlArrayList = new HsqlArrayList();
      for (int i = 0; i < arrayOfFile.length; i++) {
        if (arrayOfFile[i].exists()) {
          localHsqlArrayList.add(arrayOfFile[i]);
        }
      }
      arrayOfFile = new File[localHsqlArrayList.size()];
      localHsqlArrayList.toArray(arrayOfFile);
      return arrayOfFile;
    }
    
    public File[] getExistingFileListInDirectory()
    {
      File[] arrayOfFile = this.parent.listFiles(this);
      return arrayOfFile == null ? new File[0] : arrayOfFile;
    }
    
    public boolean accept(File paramFile, String paramString)
    {
      if ((this.parent.equals(paramFile)) && (paramString.indexOf(this.dbName) == 0))
      {
        String str = paramString.substring(this.dbName.length());
        for (int i = 0; i < this.suffixes.length; i++)
        {
          if (str.equals(this.suffixes[i])) {
            return true;
          }
          if (str.startsWith(this.suffixes[i]))
          {
            if (str.length() == this.suffixes[i].length()) {
              return true;
            }
            if (paramString.endsWith(".new"))
            {
              if (str.length() == this.suffixes[i].length() + 4) {
                return true;
              }
            }
            else if ((paramString.endsWith(".old")) && (str.length() == this.suffixes[i].length() + 9 + 4)) {
              return true;
            }
          }
        }
      }
      return false;
    }
  }
  
  public static class FileAccessRes
    implements FileAccess
  {
    public boolean isStreamElement(String paramString)
    {
      URL localURL = null;
      try
      {
        localURL = getClass().getResource(paramString);
        if (localURL == null)
        {
          ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
          if (localClassLoader != null) {
            localURL = localClassLoader.getResource(paramString);
          }
        }
      }
      catch (Throwable localThrowable) {}
      return localURL != null;
    }
    
    public InputStream openInputStreamElement(String paramString)
      throws IOException
    {
      InputStream localInputStream = null;
      try
      {
        localInputStream = getClass().getResourceAsStream(paramString);
        if (localInputStream == null)
        {
          ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
          if (localClassLoader != null) {
            localInputStream = localClassLoader.getResourceAsStream(paramString);
          }
        }
      }
      catch (Throwable localThrowable) {}finally
      {
        if (localInputStream == null) {
          throw new FileNotFoundException(paramString);
        }
      }
      return localInputStream;
    }
    
    public void createParentDirs(String paramString) {}
    
    public void removeElement(String paramString) {}
    
    public void renameElement(String paramString1, String paramString2) {}
    
    public OutputStream openOutputStreamElement(String paramString)
      throws IOException
    {
      throw new IOException();
    }
    
    public FileAccess.FileSync getFileSync(OutputStream paramOutputStream)
      throws IOException
    {
      throw new IOException();
    }
  }
  
  public static class FileSync
    implements FileAccess.FileSync
  {
    FileDescriptor outDescriptor;
    
    FileSync(FileOutputStream paramFileOutputStream)
      throws IOException
    {
      this.outDescriptor = paramFileOutputStream.getFD();
    }
    
    public void sync()
      throws IOException
    {
      this.outDescriptor.sync();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.FileUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */