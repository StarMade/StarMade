package org.hsqldb.persist;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileAccess.FileSync;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.java.JavaSystem;
import org.hsqldb.store.ValuePool;

public class HsqlProperties
{
  public static final int ANY_ERROR = 0;
  public static final int NO_VALUE_FOR_KEY = 1;
  protected String fileName;
  protected String fileExtension = "";
  protected Properties stringProps;
  protected int[] errorCodes = ValuePool.emptyIntArray;
  protected String[] errorKeys = ValuePool.emptyStringArray;
  protected boolean resource = false;
  protected FileAccess field_1710;
  protected HashMap metaData;
  public static final int indexName = 0;
  public static final int indexType = 1;
  public static final int indexClass = 2;
  public static final int indexIsRange = 3;
  public static final int indexDefaultValue = 4;
  public static final int indexRangeLow = 5;
  public static final int indexRangeHigh = 6;
  public static final int indexValues = 7;
  public static final int indexLimit = 9;
  
  public HsqlProperties()
  {
    this.stringProps = new Properties();
    this.fileName = null;
  }
  
  public HsqlProperties(String paramString)
  {
    this(paramString, ".properties");
  }
  
  public HsqlProperties(String paramString1, String paramString2)
  {
    this.stringProps = new Properties();
    this.fileName = paramString1;
    this.fileExtension = paramString2;
    this.field_1710 = FileUtil.getFileUtil();
  }
  
  public HsqlProperties(HashMap paramHashMap, String paramString, FileAccess paramFileAccess, boolean paramBoolean)
  {
    this.stringProps = new Properties();
    this.fileName = paramString;
    this.fileExtension = ".properties";
    this.field_1710 = paramFileAccess;
    this.metaData = paramHashMap;
  }
  
  public HsqlProperties(Properties paramProperties)
  {
    this.stringProps = paramProperties;
  }
  
  public void setFileName(String paramString)
  {
    this.fileName = paramString;
  }
  
  public String setProperty(String paramString, int paramInt)
  {
    return setProperty(paramString, Integer.toString(paramInt));
  }
  
  public String setProperty(String paramString, boolean paramBoolean)
  {
    return setProperty(paramString, String.valueOf(paramBoolean));
  }
  
  public String setProperty(String paramString1, String paramString2)
  {
    return (String)this.stringProps.put(paramString1, paramString2);
  }
  
  public String setPropertyIfNotExists(String paramString1, String paramString2)
  {
    paramString2 = getProperty(paramString1, paramString2);
    return setProperty(paramString1, paramString2);
  }
  
  public Properties getProperties()
  {
    return this.stringProps;
  }
  
  public String getProperty(String paramString)
  {
    return this.stringProps.getProperty(paramString);
  }
  
  public String getProperty(String paramString1, String paramString2)
  {
    return this.stringProps.getProperty(paramString1, paramString2);
  }
  
  public int getIntegerProperty(String paramString, int paramInt)
  {
    return getIntegerProperty(this.stringProps, paramString, paramInt);
  }
  
  public static int getIntegerProperty(Properties paramProperties, String paramString, int paramInt)
  {
    String str = paramProperties.getProperty(paramString);
    try
    {
      if (str != null)
      {
        str = str.trim();
        paramInt = Integer.parseInt(str);
      }
    }
    catch (NumberFormatException localNumberFormatException) {}
    return paramInt;
  }
  
  public boolean isPropertyTrue(String paramString)
  {
    return isPropertyTrue(paramString, false);
  }
  
  public boolean isPropertyTrue(String paramString, boolean paramBoolean)
  {
    String str = this.stringProps.getProperty(paramString);
    if (str == null) {
      return paramBoolean;
    }
    str = str.trim();
    return str.toLowerCase().equals("true");
  }
  
  public void removeProperty(String paramString)
  {
    this.stringProps.remove(paramString);
  }
  
  public void addProperties(Properties paramProperties)
  {
    if (paramProperties == null) {
      return;
    }
    Enumeration localEnumeration = paramProperties.propertyNames();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = paramProperties.getProperty(str1);
      this.stringProps.put(str1, str2);
    }
  }
  
  public void addProperties(HsqlProperties paramHsqlProperties)
  {
    if (paramHsqlProperties == null) {
      return;
    }
    addProperties(paramHsqlProperties.stringProps);
  }
  
  public boolean propertiesFileExists()
  {
    if (this.fileName == null) {
      return false;
    }
    String str = this.fileName + this.fileExtension;
    return this.field_1710.isStreamElement(str);
  }
  
  public boolean load()
    throws Exception
  {
    if ((this.fileName == null) || (this.fileName.length() == 0)) {
      throw new FileNotFoundException(Error.getMessage(28));
    }
    if (!propertiesFileExists()) {
      return false;
    }
    InputStream localInputStream = null;
    String str = this.fileName + this.fileExtension;
    try
    {
      localInputStream = this.field_1710.openInputStreamElement(str);
      this.stringProps.load(localInputStream);
    }
    finally
    {
      if (localInputStream != null) {
        localInputStream.close();
      }
    }
    return true;
  }
  
  public void save()
    throws Exception
  {
    if ((this.fileName == null) || (this.fileName.length() == 0)) {
      throw new FileNotFoundException(Error.getMessage(28));
    }
    String str = this.fileName + this.fileExtension;
    save(str);
  }
  
  public void save(String paramString)
    throws Exception
  {
    this.field_1710.createParentDirs(paramString);
    OutputStream localOutputStream = this.field_1710.openOutputStreamElement(paramString);
    FileAccess.FileSync localFileSync = this.field_1710.getFileSync(localOutputStream);
    JavaSystem.saveProperties(this.stringProps, "HSQL Database Engine 2.2.9", localOutputStream);
    localOutputStream.flush();
    localFileSync.sync();
    localOutputStream.close();
    localFileSync = null;
    localOutputStream = null;
  }
  
  protected void addError(int paramInt, String paramString)
  {
    this.errorCodes = ((int[])ArrayUtil.resizeArray(this.errorCodes, this.errorCodes.length + 1));
    this.errorKeys = ((String[])ArrayUtil.resizeArray(this.errorKeys, this.errorKeys.length + 1));
    this.errorCodes[(this.errorCodes.length - 1)] = paramInt;
    this.errorKeys[(this.errorKeys.length - 1)] = paramString;
  }
  
  public static HsqlProperties argArrayToProps(String[] paramArrayOfString, String paramString)
  {
    HsqlProperties localHsqlProperties = new HsqlProperties();
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      String str1 = paramArrayOfString[i];
      if ((str1.equals("--help")) || (str1.equals("-help")))
      {
        localHsqlProperties.addError(1, str1.substring(1));
      }
      else
      {
        String str2;
        if (str1.startsWith("--"))
        {
          str2 = i + 1 < paramArrayOfString.length ? paramArrayOfString[(i + 1)] : "";
          localHsqlProperties.setProperty(paramString + "." + str1.substring(2), str2);
          i++;
        }
        else if (str1.charAt(0) == '-')
        {
          str2 = i + 1 < paramArrayOfString.length ? paramArrayOfString[(i + 1)] : "";
          localHsqlProperties.setProperty(paramString + "." + str1.substring(1), str2);
          i++;
        }
      }
    }
    return localHsqlProperties;
  }
  
  public static HsqlProperties delimitedArgPairsToProps(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    HsqlProperties localHsqlProperties = new HsqlProperties();
    int j;
    for (int i = 0;; i = j + paramString3.length())
    {
      j = paramString1.indexOf(paramString3, i);
      if (j == -1) {
        j = paramString1.length();
      }
      int k = paramString1.substring(0, j).indexOf(paramString2, i);
      if (k == -1)
      {
        localHsqlProperties.addError(1, paramString1.substring(i, j).trim());
      }
      else
      {
        String str1 = paramString1.substring(i, k).trim();
        String str2 = paramString1.substring(k + paramString2.length(), j).trim();
        if (paramString4 != null) {
          str1 = paramString4 + "." + str1;
        }
        localHsqlProperties.setProperty(str1, str2);
      }
      if (j == paramString1.length()) {
        break;
      }
    }
    return localHsqlProperties;
  }
  
  public Enumeration propertyNames()
  {
    return this.stringProps.propertyNames();
  }
  
  public boolean isEmpty()
  {
    return this.stringProps.isEmpty();
  }
  
  public String[] getErrorKeys()
  {
    return this.errorKeys;
  }
  
  public void validate() {}
  
  public static Object[] getMeta(String paramString1, int paramInt, String paramString2)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = ValuePool.getInt(paramInt);
    arrayOfObject[2] = "String";
    arrayOfObject[4] = paramString2;
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt, boolean paramBoolean)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt);
    arrayOfObject[2] = "Boolean";
    arrayOfObject[4] = (paramBoolean ? Boolean.TRUE : Boolean.FALSE);
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = "Integer";
    arrayOfObject[4] = ValuePool.getInt(paramInt2);
    arrayOfObject[7] = paramArrayOfInt;
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = "Integer";
    arrayOfObject[4] = ValuePool.getInt(paramInt2);
    arrayOfObject[3] = Boolean.TRUE;
    arrayOfObject[5] = ValuePool.getInt(paramInt3);
    arrayOfObject[6] = ValuePool.getInt(paramInt4);
    return arrayOfObject;
  }
  
  public static String validateProperty(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject[2].equals("Boolean"))
    {
      paramString2 = paramString2.toLowerCase();
      if ((paramString2.equals("true")) || (paramString2.equals("false"))) {
        return null;
      }
      return "invalid boolean value for property: " + paramString1;
    }
    if (paramArrayOfObject[2].equals("String")) {
      return null;
    }
    if (paramArrayOfObject[2].equals("Integer"))
    {
      int i = Integer.parseInt(paramString2);
      if (Boolean.TRUE.equals(paramArrayOfObject[3]))
      {
        int j = ((Integer)paramArrayOfObject[5]).intValue();
        int k = ((Integer)paramArrayOfObject[6]).intValue();
        if ((i < j) || (k < i)) {
          return "value outside range for property: " + paramString1;
        }
      }
      if (paramArrayOfObject[7] != null)
      {
        int[] arrayOfInt = (int[])paramArrayOfObject[7];
        if (ArrayUtil.find(arrayOfInt, i) == -1) {
          return "value not supported for property: " + paramString1;
        }
      }
      return null;
    }
    return null;
  }
  
  public boolean validateProperty(String paramString, int paramInt)
  {
    Object[] arrayOfObject = (Object[])this.metaData.get(paramString);
    if (arrayOfObject == null) {
      return false;
    }
    if (arrayOfObject[2].equals("Integer"))
    {
      if (Boolean.TRUE.equals(arrayOfObject[3]))
      {
        int i = ((Integer)arrayOfObject[5]).intValue();
        int j = ((Integer)arrayOfObject[6]).intValue();
        if ((paramInt < i) || (j < paramInt)) {
          return false;
        }
      }
      if (arrayOfObject[7] != null)
      {
        int[] arrayOfInt = (int[])arrayOfObject[7];
        if (ArrayUtil.find(arrayOfInt, paramInt) == -1) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('[');
    int i = this.stringProps.size();
    Enumeration localEnumeration = this.stringProps.propertyNames();
    for (int j = 0; j < i; j++)
    {
      String str = (String)localEnumeration.nextElement();
      localStringBuffer.append(str);
      localStringBuffer.append('=');
      localStringBuffer.append(this.stringProps.get(str));
      if (j + 1 < i)
      {
        localStringBuffer.append(',');
        localStringBuffer.append(' ');
      }
      localStringBuffer.append(']');
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.HsqlProperties
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */