package org.hsqldb.lib.tar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;
import org.hsqldb.lib.InputStreamInterface;

public class DbBackup
{
  protected File dbDir;
  protected File archiveFile;
  protected String instanceName;
  protected boolean overWrite = false;
  protected boolean abortUponModify = true;
  File[] componentFiles;
  InputStreamInterface[] componentStreams;
  boolean[] existList;
  boolean[] ignoreList;

  public static void main(String[] paramArrayOfString)
    throws IOException, TarMalformatException
  {
    try
    {
      if (paramArrayOfString.length < 1)
      {
        System.out.println(RB.DbBackup_syntax.getString(new String[] { DbBackup.class.getName() }));
        System.out.println();
        System.out.println(RB.listing_format.getString());
        System.exit(0);
      }
      if (paramArrayOfString[0].equals("--save"))
      {
        boolean bool = (paramArrayOfString.length > 1) && (paramArrayOfString[1].equals("--overwrite"));
        if (paramArrayOfString.length != (bool ? 4 : 3))
          throw new IllegalArgumentException();
        DbBackup localDbBackup = new DbBackup(new File(paramArrayOfString[(paramArrayOfString.length - 2)]), paramArrayOfString[(paramArrayOfString.length - 1)]);
        localDbBackup.setOverWrite(bool);
        localDbBackup.write();
      }
      else
      {
        int j;
        if (paramArrayOfString[0].equals("--list"))
        {
          if (paramArrayOfString.length < 2)
            throw new IllegalArgumentException();
          String[] arrayOfString1 = null;
          if (paramArrayOfString.length > 2)
          {
            arrayOfString1 = new String[paramArrayOfString.length - 2];
            for (j = 2; j < paramArrayOfString.length; j++)
              arrayOfString1[(j - 2)] = paramArrayOfString[j];
          }
          new TarReader(new File(paramArrayOfString[1]), 0, arrayOfString1, new Integer(generateBufferBlockValue(new File(paramArrayOfString[1]))), null).read();
        }
        else if (paramArrayOfString[0].equals("--extract"))
        {
          int i = (paramArrayOfString.length > 1) && (paramArrayOfString[1].equals("--overwrite")) ? 1 : 0;
          j = i != 0 ? 4 : 3;
          if (paramArrayOfString.length < j)
            throw new IllegalArgumentException();
          String[] arrayOfString2 = null;
          if (paramArrayOfString.length > j)
          {
            arrayOfString2 = new String[paramArrayOfString.length - j];
            for (int k = j; k < paramArrayOfString.length; k++)
              arrayOfString2[(k - j)] = paramArrayOfString[k];
          }
          File localFile = new File(paramArrayOfString[1]);
          int m = i != 0 ? 2 : 1;
          new TarReader(localFile, m, arrayOfString2, new Integer(generateBufferBlockValue(localFile)), new File(paramArrayOfString[(j - 1)])).read();
        }
        else
        {
          throw new IllegalArgumentException();
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      System.out.println(RB.DbBackup_syntaxerr.getString(new String[] { DbBackup.class.getName() }));
      System.exit(2);
    }
  }

  public DbBackup(File paramFile, String paramString)
  {
    this.archiveFile = paramFile;
    File localFile = new File(paramString);
    this.dbDir = localFile.getAbsoluteFile().getParentFile();
    this.instanceName = localFile.getName();
    this.componentFiles = new File[] { new File(this.dbDir, this.instanceName + ".properties"), new File(this.dbDir, this.instanceName + ".script"), new File(this.dbDir, this.instanceName + ".data"), new File(this.dbDir, this.instanceName + ".backup"), new File(this.dbDir, this.instanceName + ".log"), new File(this.dbDir, this.instanceName + ".lobs") };
    this.componentStreams = new InputStreamInterface[this.componentFiles.length];
    this.existList = new boolean[this.componentFiles.length];
    this.ignoreList = new boolean[this.componentFiles.length];
  }

  public DbBackup(File paramFile, String paramString, boolean paramBoolean)
  {
    this.archiveFile = paramFile;
    File localFile = new File(paramString);
    this.dbDir = localFile.getAbsoluteFile().getParentFile();
    this.instanceName = localFile.getName();
    this.componentFiles = new File[] { new File(this.dbDir, this.instanceName + ".script") };
    this.componentStreams = new InputStreamInterface[this.componentFiles.length];
    this.existList = new boolean[this.componentFiles.length];
    this.abortUponModify = false;
  }

  public void setStream(String paramString, InputStreamInterface paramInputStreamInterface)
  {
    for (int i = 0; i < this.componentFiles.length; i++)
      if (this.componentFiles[i].getName().endsWith(paramString))
      {
        this.componentStreams[i] = paramInputStreamInterface;
        break;
      }
  }

  public void setFileIgnore(String paramString)
  {
    for (int i = 0; i < this.componentFiles.length; i++)
      if (this.componentFiles[i].getName().endsWith(paramString))
      {
        this.ignoreList[i] = true;
        break;
      }
  }

  public void setOverWrite(boolean paramBoolean)
  {
    this.overWrite = paramBoolean;
  }

  public void setAbortUponModify(boolean paramBoolean)
  {
    this.abortUponModify = paramBoolean;
  }

  public boolean getOverWrite()
  {
    return this.overWrite;
  }

  public boolean getAbortUponModify()
  {
    return this.abortUponModify;
  }

  public void write()
    throws IOException, TarMalformatException
  {
    long l = new Date().getTime();
    checkEssentialFiles();
    TarGenerator localTarGenerator = new TarGenerator(this.archiveFile, this.overWrite, new Integer(generateBufferBlockValue(this.componentFiles)));
    for (int i = 0; i < this.componentFiles.length; i++)
    {
      int j = (this.componentStreams[i] != null) || (this.componentFiles[i].exists()) ? 1 : 0;
      if ((j != 0) && (this.ignoreList[i] == 0))
        if (this.componentStreams[i] == null)
        {
          localTarGenerator.queueEntry(this.componentFiles[i].getName(), this.componentFiles[i]);
          this.existList[i] = true;
        }
        else
        {
          localTarGenerator.queueEntry(this.componentFiles[i].getName(), this.componentStreams[i]);
        }
    }
    localTarGenerator.write();
    checkFilesNotChanged(l);
  }

  void checkEssentialFiles()
    throws FileNotFoundException, IllegalStateException
  {
    if (!this.componentFiles[0].getName().endsWith(".properties"))
      return;
    for (int i = 0; i < 2; i++)
    {
      int j = (this.componentStreams[i] != null) || (this.componentFiles[i].exists()) ? 1 : 0;
      if (j == 0)
        throw new FileNotFoundException(RB.file_missing.getString(new String[] { this.componentFiles[i].getAbsolutePath() }));
    }
    if (!this.abortUponModify)
      return;
    Properties localProperties = new Properties();
    FileInputStream localFileInputStream = null;
    try
    {
      File localFile = this.componentFiles[0];
      localFileInputStream = new FileInputStream(localFile);
      localProperties.load(localFileInputStream);
    }
    catch (IOException localIOException2)
    {
    }
    finally
    {
      try
      {
        if (localFileInputStream != null)
          localFileInputStream.close();
      }
      catch (IOException localIOException4)
      {
      }
      finally
      {
        localFileInputStream = null;
      }
    }
    String str = localProperties.getProperty("modified");
    if ((str != null) && ((str.equalsIgnoreCase("yes")) || (str.equalsIgnoreCase("true"))))
      throw new IllegalStateException(RB.modified_property.getString(new String[] { str }));
  }

  void checkFilesNotChanged(long paramLong)
    throws FileNotFoundException
  {
    if (!this.abortUponModify)
      return;
    try
    {
      for (int i = 0; i < this.componentFiles.length; i++)
        if (this.componentFiles[i].exists())
        {
          if (this.existList[i] == 0)
            throw new FileNotFoundException(RB.file_disappeared.getString(new String[] { this.componentFiles[i].getAbsolutePath() }));
          if (this.componentFiles[i].lastModified() > paramLong)
            throw new FileNotFoundException(RB.file_changed.getString(new String[] { this.componentFiles[i].getAbsolutePath() }));
        }
        else if (this.existList[i] != 0)
        {
          throw new FileNotFoundException(RB.file_appeared.getString(new String[] { this.componentFiles[i].getAbsolutePath() }));
        }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      if (!this.archiveFile.delete())
        System.out.println(RB.cleanup_rmfail.getString(new String[] { this.archiveFile.getAbsolutePath() }));
      throw localIllegalStateException;
    }
  }

  protected static int generateBufferBlockValue(File[] paramArrayOfFile)
  {
    long l = 0L;
    for (int i = 0; i < paramArrayOfFile.length; i++)
      if ((paramArrayOfFile[i] != null) && (paramArrayOfFile[i].length() > l))
        l = paramArrayOfFile[i].length();
    i = (int)(l / 5120L);
    if (i < 1)
      return 1;
    if (i > 40960)
      return 40960;
    return i;
  }

  protected static int generateBufferBlockValue(File paramFile)
  {
    return generateBufferBlockValue(new File[] { paramFile });
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.DbBackup
 * JD-Core Version:    0.6.2
 */