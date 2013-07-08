package org.hsqldb.lib.java;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.util.Properties;

public class JavaSystem
{
  public static int gcFrequency;
  public static int memoryRecords;
  static final BigDecimal BD_1 = BigDecimal.valueOf(1L);
  static final BigDecimal MBD_1 = BigDecimal.valueOf(-1L);
  
  public static void gc()
  {
    if ((gcFrequency > 0) && (memoryRecords > gcFrequency))
    {
      memoryRecords = 0;
      System.gc();
    }
  }
  
  public static IOException toIOException(Throwable paramThrowable)
  {
    if ((paramThrowable instanceof IOException)) {
      return (IOException)paramThrowable;
    }
    return new IOException(paramThrowable);
  }
  
  public static int precision(BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null) {
      return 0;
    }
    int i;
    if ((paramBigDecimal.compareTo(BD_1) < 0) && (paramBigDecimal.compareTo(MBD_1) > 0)) {
      i = paramBigDecimal.scale();
    } else {
      i = paramBigDecimal.precision();
    }
    return i;
  }
  
  public static String toString(BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null) {
      return null;
    }
    return paramBigDecimal.toPlainString();
  }
  
  public static int compareIngnoreCase(String paramString1, String paramString2)
  {
    return paramString1.compareToIgnoreCase(paramString2);
  }
  
  public static double parseDouble(String paramString)
  {
    return Double.parseDouble(paramString);
  }
  
  public static BigInteger unscaledValue(BigDecimal paramBigDecimal)
  {
    return paramBigDecimal.unscaledValue();
  }
  
  public static void setLogToSystem(boolean paramBoolean)
  {
    try
    {
      PrintWriter localPrintWriter = paramBoolean ? new PrintWriter(System.out) : null;
      DriverManager.setLogWriter(localPrintWriter);
    }
    catch (Exception localException) {}
  }
  
  public static void deleteOnExit(File paramFile)
  {
    paramFile.deleteOnExit();
  }
  
  public static void saveProperties(Properties paramProperties, String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    paramProperties.store(paramOutputStream, paramString);
  }
  
  public static void runFinalizers()
  {
    System.runFinalizersOnExit(true);
  }
  
  public static boolean createNewFile(File paramFile)
  {
    try
    {
      return paramFile.createNewFile();
    }
    catch (IOException localIOException) {}
    return false;
  }
  
  public static void setRAFileLength(RandomAccessFile paramRandomAccessFile, long paramLong)
    throws IOException
  {
    paramRandomAccessFile.setLength(paramLong);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.java.JavaSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */