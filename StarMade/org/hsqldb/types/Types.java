package org.hsqldb.types;

import org.hsqldb.error.Error;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.IntValueHashMap;

public class Types
{
  public static final String DecimalClassName = "java.math.BigDecimal";
  public static final String DateClassName = "java.sql.Date";
  public static final String TimeClassName = "java.sql.Time";
  public static final String TimestampClassName = "java.sql.Timestamp";
  public static final String BlobClassName = "java.sql.Blob";
  public static final String ClobClassName = "java.sql.Clob";
  public static final int SQL_CHAR = 1;
  public static final int SQL_NUMERIC = 2;
  public static final int SQL_DECIMAL = 3;
  public static final int SQL_INTEGER = 4;
  public static final int SQL_SMALLINT = 5;
  public static final int SQL_FLOAT = 6;
  public static final int SQL_REAL = 7;
  public static final int SQL_DOUBLE = 8;
  public static final int SQL_VARCHAR = 12;
  public static final int SQL_BOOLEAN = 16;
  public static final int SQL_USER_DEFINED_TYPE = 17;
  public static final int SQL_ROW = 19;
  public static final int SQL_REF = 20;
  public static final int SQL_BIGINT = 25;
  public static final int SQL_BLOB = 30;
  public static final int SQL_CLOB = 40;
  public static final int SQL_ARRAY = 50;
  public static final int SQL_MULTISET = 55;
  public static final int SQL_BINARY = 60;
  public static final int SQL_VARBINARY = 61;
  public static final int SQL_DATE = 91;
  public static final int SQL_TIME = 92;
  public static final int SQL_TIMESTAMP = 93;
  public static final int SQL_TIME_WITH_TIME_ZONE = 94;
  public static final int SQL_TIMESTAMP_WITH_TIME_ZONE = 95;
  public static final int SQL_INTERVAL_YEAR = 101;
  public static final int SQL_INTERVAL_MONTH = 102;
  public static final int SQL_INTERVAL_DAY = 103;
  public static final int SQL_INTERVAL_HOUR = 104;
  public static final int SQL_INTERVAL_MINUTE = 105;
  public static final int SQL_INTERVAL_SECOND = 106;
  public static final int SQL_INTERVAL_YEAR_TO_MONTH = 107;
  public static final int SQL_INTERVAL_DAY_TO_HOUR = 108;
  public static final int SQL_INTERVAL_DAY_TO_MINUTE = 109;
  public static final int SQL_INTERVAL_DAY_TO_SECOND = 110;
  public static final int SQL_INTERVAL_HOUR_TO_MINUTE = 111;
  public static final int SQL_INTERVAL_HOUR_TO_SECOND = 112;
  public static final int SQL_INTERVAL_MINUTE_TO_SECOND = 113;
  public static final int SQL_TYPE_NUMBER_LIMIT = 256;
  public static final int SQL_BIT = 14;
  public static final int SQL_BIT_VARYING = 15;
  public static final int SQL_DATALINK = 70;
  public static final int SQL_UDT = 17;
  public static final int SQL_UDT_LOCATOR = 18;
  public static final int SQL_BLOB_LOCATOR = 31;
  public static final int SQL_CLOB_LOCATOR = 41;
  public static final int SQL_ARRAY_LOCATOR = 51;
  public static final int SQL_MULTISET_LOCATOR = 56;
  public static final int SQL_ALL_TYPES = 0;
  public static final int SQL_DATETIME = 9;
  public static final int SQL_INTERVAL = 10;
  public static final int SQL_XML = 137;
  public static final int SQL_NCHAR = -8;
  public static final int SQL_WCHAR = -8;
  public static final int SQL_WVARCHAR = -9;
  public static final int SQL_NVARCHAR = -9;
  public static final int SQL_WLONGVARCHAR = -10;
  public static final int SQL_NTEXT = -10;
  public static final int SQL_LONGVARBINARY = -4;
  public static final int SQL_IMAGE = -4;
  public static final int SQL_GUID = -11;
  public static final int SQL_VARIANT = -150;
  public static final int SQL_SUB_DISTINCT = 1;
  public static final int SQL_SUB_STRUCTURED = 2;
  public static final int VARCHAR_IGNORECASE = 100;
  public static final int ARRAY = 2003;
  public static final int BIGINT = -5;
  public static final int BINARY = -2;
  public static final int BIT = -7;
  public static final int BLOB = 2004;
  public static final int BOOLEAN = 16;
  public static final int CHAR = 1;
  public static final int CLOB = 2005;
  public static final int DATALINK = 70;
  public static final int DATE = 91;
  public static final int DECIMAL = 3;
  public static final int DISTINCT = 2001;
  public static final int DOUBLE = 8;
  public static final int FLOAT = 6;
  public static final int INTEGER = 4;
  public static final int JAVA_OBJECT = 2000;
  public static final int LONGVARBINARY = -4;
  public static final int LONGVARCHAR = -1;
  public static final int MULTISET = 0;
  public static final int NULL = 0;
  public static final int NUMERIC = 2;
  public static final int OTHER = 1111;
  public static final int REAL = 7;
  public static final int REF = 2006;
  public static final int SMALLINT = 5;
  public static final int STRUCT = 2002;
  public static final int TIME = 92;
  public static final int TIMESTAMP = 93;
  public static final int TINYINT = -6;
  public static final int VARBINARY = -3;
  public static final int VARCHAR = 12;
  public static final int ROWID = 2008;
  public static final int NCHAR = -8;
  public static final int NVARCHAR = -9;
  public static final int LONGNVARCHAR = -10;
  public static final int NCLOB = 2007;
  public static final int SQLXML = 2009;
  public static final int TYPE_SUB_DEFAULT = 1;
  public static final int TYPE_SUB_IGNORECASE = 4;
  public static final int[][] ALL_TYPES = { { 50, 1 }, { 25, 1 }, { 60, 1 }, { 61, 1 }, { 30, 1 }, { 16, 1 }, { 1, 1 }, { 40, 1 }, { 70, 1 }, { 91, 1 }, { 3, 1 }, { 2001, 1 }, { 8, 1 }, { 6, 1 }, { 4, 1 }, { 2000, 1 }, { -8, 1 }, { 2007, 1 }, { 0, 1 }, { 2, 1 }, { -9, 1 }, { 1111, 1 }, { 7, 1 }, { 20, 1 }, { 2008, 1 }, { 5, 1 }, { 2002, 1 }, { 92, 1 }, { 93, 1 }, { -6, 1 }, { 12, 1 }, { 12, 4 }, { 137, 1 } };
  static final IntValueHashMap javaTypeNumbers = new IntValueHashMap(32);
  private static final HashSet illegalParameterClasses;
  public static final int MAX_CHAR_OR_VARCHAR_DISPLAY_SIZE = MAX_CHAR_OR_VARCHAR_DISPLAY_SIZE();
  
  public static Type getParameterSQLType(Class paramClass)
  {
    if (paramClass == null) {
      throw Error.runtimeError(201, "Types");
    }
    if (Void.TYPE.equals(paramClass)) {
      return Type.SQL_ALL_TYPES;
    }
    String str = paramClass.getName();
    int i = javaTypeNumbers.get(str, -2147483648);
    if (i != -2147483648) {
      return Type.getDefaultTypeWithSize(i);
    }
    if (paramClass.isArray())
    {
      Class localClass = paramClass.getComponentType();
      str = localClass.getName();
      i = javaTypeNumbers.get(str, -2147483648);
      if (i != -2147483648) {
        return Type.getDefaultTypeWithSize(i);
      }
      if (i == 0) {
        return null;
      }
      return Type.getDefaultTypeWithSize(i);
    }
    if (str.equals("java.sql.Array")) {
      return Type.getDefaultArrayType(0);
    }
    return null;
  }
  
  public static boolean acceptsZeroPrecision(int paramInt)
  {
    switch (paramInt)
    {
    case 92: 
    case 93: 
      return true;
    }
    return false;
  }
  
  public static boolean requiresPrecision(int paramInt)
  {
    switch (paramInt)
    {
    case -9: 
    case 12: 
    case 15: 
    case 61: 
      return true;
    }
    return false;
  }
  
  public static boolean acceptsPrecision(int paramInt)
  {
    switch (paramInt)
    {
    case -9: 
    case -8: 
    case -4: 
    case -1: 
    case 1: 
    case 2: 
    case 3: 
    case 6: 
    case 12: 
    case 14: 
    case 15: 
    case 30: 
    case 40: 
    case 50: 
    case 60: 
    case 61: 
    case 92: 
    case 93: 
    case 100: 
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 107: 
    case 108: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 113: 
    case 2007: 
      return true;
    }
    return false;
  }
  
  public static boolean acceptsScaleCreateParam(int paramInt)
  {
    switch (paramInt)
    {
    case 106: 
      return true;
    case 2: 
    case 3: 
      return true;
    }
    return false;
  }
  
  private static int MAX_CHAR_OR_VARCHAR_DISPLAY_SIZE()
  {
    try
    {
      return Integer.getInteger("hsqldb.max_char_or_varchar_display_size", 32766).intValue();
    }
    catch (SecurityException localSecurityException) {}
    return 32766;
  }
  
  public static boolean isSearchable(int paramInt)
  {
    switch (paramInt)
    {
    case 30: 
    case 40: 
    case 1111: 
    case 2000: 
    case 2002: 
    case 2007: 
    case 2008: 
      return false;
    }
    return true;
  }
  
  static
  {
    javaTypeNumbers.put("int", 4);
    javaTypeNumbers.put("java.lang.Integer", 4);
    javaTypeNumbers.put("double", 8);
    javaTypeNumbers.put("java.lang.Double", 8);
    javaTypeNumbers.put("java.lang.String", 12);
    javaTypeNumbers.put("java.sql.Date", 91);
    javaTypeNumbers.put("java.sql.Time", 92);
    javaTypeNumbers.put("java.sql.Timestamp", 93);
    javaTypeNumbers.put("java.sql.Blob", 30);
    javaTypeNumbers.put("java.sql.Clob", 40);
    javaTypeNumbers.put("java.util.Date", 91);
    javaTypeNumbers.put("java.math.BigDecimal", 3);
    javaTypeNumbers.put("boolean", 16);
    javaTypeNumbers.put("java.lang.Boolean", 16);
    javaTypeNumbers.put("byte", -6);
    javaTypeNumbers.put("java.lang.Byte", -6);
    javaTypeNumbers.put("short", 5);
    javaTypeNumbers.put("java.lang.Short", 5);
    javaTypeNumbers.put("long", 25);
    javaTypeNumbers.put("java.lang.Long", 25);
    javaTypeNumbers.put("[B", 60);
    javaTypeNumbers.put("java.lang.Object", 1111);
    javaTypeNumbers.put("java.lang.Void", 0);
    illegalParameterClasses = new HashSet();
    illegalParameterClasses.add(Byte.TYPE);
    illegalParameterClasses.add(Short.TYPE);
    illegalParameterClasses.add(Float.TYPE);
    illegalParameterClasses.add(Byte.class);
    illegalParameterClasses.add(Short.class);
    illegalParameterClasses.add(Float.class);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.Types
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */