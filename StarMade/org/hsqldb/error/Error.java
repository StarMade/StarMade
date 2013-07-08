package org.hsqldb.error;

import java.io.PrintStream;
import java.lang.reflect.Field;
import org.hsqldb.HsqlException;
import org.hsqldb.lib.StringUtil;
import org.hsqldb.resources.BundleHandler;
import org.hsqldb.result.Result;

public class Error
{
  public static boolean TRACE = false;
  public static boolean TRACESYSTEMOUT = false;
  private static final String errPropsName = "sql-state-messages";
  private static final int bundleHandle = BundleHandler.getBundleHandle("sql-state-messages", null);
  private static final String MESSAGE_TAG = "$$";
  private static final int SQL_STATE_DIGITS = 5;
  private static final int SQL_CODE_DIGITS = 4;
  private static final int ERROR_CODE_BASE = 11;
  
  public static RuntimeException runtimeError(int paramInt, String paramString)
  {
    HsqlException localHsqlException = error(paramInt, paramString);
    return new RuntimeException(localHsqlException.getMessage());
  }
  
  public static HsqlException error(int paramInt, String paramString)
  {
    return error((Throwable)null, paramInt, paramString);
  }
  
  public static HsqlException error(Throwable paramThrowable, int paramInt, String paramString)
  {
    String str = getMessage(paramInt);
    if (paramString != null) {
      str = str + ": " + paramString.toString();
    }
    return new HsqlException(paramThrowable, str.substring(6), str.substring(0, 5), -paramInt);
  }
  
  public static HsqlException parseError(int paramInt1, String paramString, int paramInt2)
  {
    String str = getMessage(paramInt1);
    if (paramString != null) {
      str = str + ": " + paramString;
    }
    if (paramInt2 > 1)
    {
      paramString = getMessage(24);
      str = str + " :" + paramString + String.valueOf(paramInt2);
    }
    return new HsqlException(null, str.substring(6), str.substring(0, 5), -paramInt1);
  }
  
  public static HsqlException error(int paramInt)
  {
    return error(null, paramInt, 0, null);
  }
  
  public static HsqlException error(int paramInt, Throwable paramThrowable)
  {
    String str = getMessage(paramInt, 0, null);
    return new HsqlException(paramThrowable, str.substring(0, 5), -paramInt);
  }
  
  public static HsqlException error(Throwable paramThrowable, int paramInt1, int paramInt2, Object[] paramArrayOfObject)
  {
    String str = getMessage(paramInt1, paramInt2, paramArrayOfObject);
    int i = paramInt2 < 11 ? paramInt1 : paramInt2;
    return new HsqlException(paramThrowable, str.substring(6), str.substring(0, 5), -i);
  }
  
  public static HsqlException parseError(int paramInt1, int paramInt2, int paramInt3, Object[] paramArrayOfObject)
  {
    String str1 = getMessage(paramInt1, paramInt2, paramArrayOfObject);
    if (paramInt3 > 1)
    {
      String str2 = getMessage(24);
      str1 = str1 + " :" + str2 + String.valueOf(paramInt3);
    }
    int i = paramInt2 < 11 ? paramInt1 : paramInt2;
    return new HsqlException(null, str1.substring(6), str1.substring(0, 5), -i);
  }
  
  public static HsqlException error(int paramInt1, int paramInt2)
  {
    return error(paramInt1, getMessage(paramInt2));
  }
  
  public static HsqlException error(String paramString1, String paramString2)
  {
    int i = getCode(paramString2);
    if (i < 1000) {
      i = 5800;
    }
    if (paramString1 == null) {
      paramString1 = getMessage(i);
    }
    return new HsqlException(null, paramString1, paramString2, i);
  }
  
  private static String insertStrings(String paramString, Object[] paramArrayOfObject)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramString.length() + 32);
    int i = 0;
    int j = paramString.length();
    for (int k = 0; k < paramArrayOfObject.length; k++)
    {
      j = paramString.indexOf("$$", i);
      if (j == -1) {
        break;
      }
      localStringBuffer.append(paramString.substring(i, j));
      localStringBuffer.append(paramArrayOfObject[k] == null ? "null exception message" : paramArrayOfObject[k].toString());
      i = j + "$$".length();
    }
    j = paramString.length();
    localStringBuffer.append(paramString.substring(i, j));
    return localStringBuffer.toString();
  }
  
  public static String getMessage(int paramInt)
  {
    return getResourceString(paramInt);
  }
  
  public static String getStateString(int paramInt)
  {
    return getMessage(paramInt, 0, null).substring(0, 5);
  }
  
  public static String getMessage(int paramInt1, int paramInt2, Object[] paramArrayOfObject)
  {
    String str = getResourceString(paramInt1);
    if (paramInt2 != 0) {
      str = str + getResourceString(paramInt2);
    }
    if (paramArrayOfObject != null) {
      str = insertStrings(str, paramArrayOfObject);
    }
    return str;
  }
  
  private static String getResourceString(int paramInt)
  {
    String str = StringUtil.toZeroPaddedString(paramInt, 4, 4);
    return BundleHandler.getString(bundleHandle, str);
  }
  
  public static HsqlException error(Result paramResult)
  {
    return new HsqlException(paramResult);
  }
  
  public static void printSystemOut(String paramString)
  {
    if (TRACESYSTEMOUT) {
      System.out.println(paramString);
    }
  }
  
  public static int getCode(String paramString)
  {
    try
    {
      Field[] arrayOfField = ErrorCode.class.getDeclaredFields();
      for (int i = 0; i < arrayOfField.length; i++)
      {
        String str = arrayOfField[i].getName();
        if ((str.length() == 7) && (str.endsWith(paramString))) {
          return arrayOfField[i].getInt(ErrorCode.class);
        }
      }
    }
    catch (IllegalAccessException localIllegalAccessException) {}
    return -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.error.Error
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */