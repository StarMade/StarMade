package org.hsqldb.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.hsqldb.HsqlDateTime;

public class SimpleLog
{
  public static final int LOG_NONE = 0;
  public static final int LOG_ERROR = 1;
  public static final int LOG_NORMAL = 2;
  public static final int LOG_DETAIL = 3;
  public static final String logTypeNameEngine = "ENGINE";
  public static final String[] appLogTypeNames = { "", "ERROR ", "NORMAL", "DETAIL" };
  public static final String[] sqlLogTypeNames = { "", "BASIC ", "NORMAL", "DETAIL" };
  private PrintWriter writer;
  private int level;
  private boolean isSystem;
  private boolean isSQL;
  String[] logTypeNames;
  private String filePath;
  private StringBuffer field_2103;
  
  public SimpleLog(String paramString, int paramInt, boolean paramBoolean)
  {
    this.isSystem = (paramString == null);
    this.filePath = paramString;
    this.isSQL = paramBoolean;
    this.logTypeNames = (paramBoolean ? sqlLogTypeNames : appLogTypeNames);
    this.field_2103 = new StringBuffer(256);
    setLevel(paramInt);
  }
  
  private void setupWriter()
  {
    if (this.level == 0)
    {
      close();
      return;
    }
    if (this.writer == null) {
      if (this.isSystem)
      {
        this.writer = new PrintWriter(System.out);
      }
      else
      {
        File localFile = new File(this.filePath);
        setupLog(localFile);
      }
    }
  }
  
  private void setupLog(File paramFile)
  {
    try
    {
      FileUtil.getFileUtil().makeParentDirectories(paramFile);
      this.writer = new PrintWriter(new FileWriter(paramFile, true), true);
    }
    catch (Exception localException)
    {
      this.isSystem = true;
      this.writer = new PrintWriter(System.out);
    }
  }
  
  public int getLevel()
  {
    return this.level;
  }
  
  public void setLevel(int paramInt)
  {
    this.level = paramInt;
    setupWriter();
  }
  
  public PrintWriter getPrintWriter()
  {
    return this.writer;
  }
  
  public synchronized void logContext(int paramInt, String paramString)
  {
    if (this.level < paramInt) {
      return;
    }
    this.field_2103.append(HsqlDateTime.getSystemTimeString()).append(' ');
    this.field_2103.append(this.logTypeNames[paramInt]).append(' ').append(paramString);
    this.writer.println(this.field_2103.toString());
    this.field_2103.setLength(0);
  }
  
  public synchronized void logContext(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    if (this.level < paramInt) {
      return;
    }
    this.field_2103.append(HsqlDateTime.getSystemTimeString()).append(' ');
    this.field_2103.append(this.logTypeNames[paramInt]).append(' ').append(paramString1);
    this.field_2103.append(' ').append(paramString2).append(' ').append(paramString3);
    this.writer.println(this.field_2103.toString());
    this.field_2103.setLength(0);
  }
  
  public synchronized void logContext(Throwable paramThrowable, String paramString, int paramInt)
  {
    if (this.level == 0) {
      return;
    }
    if (this.writer == null) {
      return;
    }
    this.field_2103.append(HsqlDateTime.getSystemTimeString()).append(' ');
    this.field_2103.append(this.logTypeNames[paramInt]).append(' ').append(paramString);
    Throwable localThrowable = new Throwable();
    StackTraceElement[] arrayOfStackTraceElement = localThrowable.getStackTrace();
    if (arrayOfStackTraceElement.length > 1)
    {
      this.field_2103.append(' ');
      this.field_2103.append(arrayOfStackTraceElement[1].getClassName()).append('.');
      this.field_2103.append(arrayOfStackTraceElement[1].getMethodName());
    }
    arrayOfStackTraceElement = paramThrowable.getStackTrace();
    if (arrayOfStackTraceElement.length > 0)
    {
      this.field_2103.append(' ');
      this.field_2103.append(arrayOfStackTraceElement[0].getClassName()).append('.');
      this.field_2103.append(' ').append(arrayOfStackTraceElement[0].getMethodName());
    }
    this.field_2103.append(' ').append(paramThrowable.toString());
    this.writer.println(this.field_2103.toString());
    this.field_2103.setLength(0);
  }
  
  public void flush()
  {
    if (this.writer != null) {
      this.writer.flush();
    }
  }
  
  public void close()
  {
    if ((this.writer != null) && (!this.isSystem))
    {
      this.writer.flush();
      this.writer.close();
    }
    this.writer = null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.SimpleLog
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */