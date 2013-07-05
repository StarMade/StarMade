package org.hsqldb.store;

import java.math.BigDecimal;

public class ValuePool
{
  static ValuePoolHashMap intPool;
  static ValuePoolHashMap longPool;
  static ValuePoolHashMap doublePool;
  static ValuePoolHashMap bigdecimalPool;
  static ValuePoolHashMap stringPool;
  static final int SPACE_STRING_SIZE = 64;
  static final int DEFAULT_VALUE_POOL_SIZE = 4096;
  static final int[] defaultPoolLookupSize = { 4096, 4096, 4096, 4096, 4096 };
  static final int POOLS_COUNT = defaultPoolLookupSize.length;
  static final int defaultSizeFactor = 2;
  static final int defaultMaxStringLength = 16;
  static ValuePoolHashMap[] poolList;
  static int maxStringLength;
  public static final Integer INTEGER_0 = getInt(0);
  public static final Integer INTEGER_1 = getInt(1);
  public static final Integer INTEGER_2 = getInt(2);
  public static final Integer INTEGER_MAX = getInt(2147483647);
  public static final BigDecimal BIG_DECIMAL_0 = getBigDecimal(new BigDecimal(0.0D));
  public static final BigDecimal BIG_DECIMAL_1 = getBigDecimal(new BigDecimal(1.0D));
  public static final String[] emptyStringArray = new String[0];
  public static final Object[] emptyObjectArray = new Object[0];
  public static final int[] emptyIntArray = new int[0];
  public static String spaceString;

  private static void initPool()
  {
    int[] arrayOfInt = defaultPoolLookupSize;
    int i = 2;
    synchronized (ValuePool.class)
    {
      maxStringLength = 16;
      poolList = new ValuePoolHashMap[POOLS_COUNT];
      for (int j = 0; j < POOLS_COUNT; j++)
      {
        k = arrayOfInt[j];
        poolList[j] = new ValuePoolHashMap(k, k * i, 2);
      }
      intPool = poolList[0];
      longPool = poolList[1];
      doublePool = poolList[2];
      bigdecimalPool = poolList[3];
      stringPool = poolList[4];
      char[] arrayOfChar = new char[64];
      for (int k = 0; k < 64; k++)
        arrayOfChar[k] = ' ';
      spaceString = new String(arrayOfChar);
    }
  }

  public static int getMaxStringLength()
  {
    return maxStringLength;
  }

  public static void resetPool(int[] paramArrayOfInt, int paramInt)
  {
    synchronized (ValuePool.class)
    {
      for (int i = 0; i < POOLS_COUNT; i++)
      {
        poolList[i].clear();
        poolList[i].resetCapacity(paramArrayOfInt[i] * paramInt, 2);
      }
    }
  }

  public static void resetPool()
  {
    synchronized (ValuePool.class)
    {
      resetPool(defaultPoolLookupSize, 2);
    }
  }

  public static void clearPool()
  {
    synchronized (ValuePool.class)
    {
      for (int i = 0; i < POOLS_COUNT; i++)
        poolList[i].clear();
    }
  }

  public static Integer getInt(int paramInt)
  {
    synchronized (intPool)
    {
      return intPool.getOrAddInteger(paramInt);
    }
  }

  public static Long getLong(long paramLong)
  {
    synchronized (longPool)
    {
      return longPool.getOrAddLong(paramLong);
    }
  }

  public static Double getDouble(long paramLong)
  {
    synchronized (doublePool)
    {
      return doublePool.getOrAddDouble(paramLong);
    }
  }

  public static String getString(String paramString)
  {
    if ((paramString == null) || (paramString.length() > maxStringLength))
      return paramString;
    synchronized (stringPool)
    {
      return stringPool.getOrAddString(paramString);
    }
  }

  public static String getSubString(String paramString, int paramInt1, int paramInt2)
  {
    synchronized (stringPool)
    {
      return stringPool.getOrAddString(paramString.substring(paramInt1, paramInt2));
    }
  }

  public static BigDecimal getBigDecimal(BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null)
      return paramBigDecimal;
    synchronized (bigdecimalPool)
    {
      return (BigDecimal)bigdecimalPool.getOrAddObject(paramBigDecimal);
    }
  }

  public static Boolean getBoolean(boolean paramBoolean)
  {
    return paramBoolean ? Boolean.TRUE : Boolean.FALSE;
  }

  static
  {
    initPool();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.store.ValuePool
 * JD-Core Version:    0.6.2
 */