package org.hsqldb.store;

import org.hsqldb.types.TimestampData;

public class ValuePoolHashMap extends BaseHashMap
{
  public ValuePoolHashMap(int paramInt1, int paramInt2, int paramInt3)
    throws IllegalArgumentException
  {
    super(paramInt1, 3, 0, true);
    this.maxCapacity = paramInt2;
    this.purgePolicy = paramInt3;
  }

  public void resetCapacity(int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    if ((paramInt1 != 0) && (this.hashIndex.elementCount > paramInt1))
    {
      int i = this.hashIndex.elementCount - paramInt1;
      i += (i >> 5);
      if (i > this.hashIndex.elementCount)
        i = this.hashIndex.elementCount;
      clear(i, i >> 6);
    }
    if ((paramInt1 != 0) && (paramInt1 < this.threshold))
    {
      rehash(paramInt1);
      if (paramInt1 < this.hashIndex.elementCount)
        paramInt1 = this.maxCapacity;
    }
    this.maxCapacity = paramInt1;
    this.purgePolicy = paramInt2;
  }

  protected Integer getOrAddInteger(int paramInt)
  {
    int i = this.hashIndex.getHashIndex(paramInt);
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      localInteger = (Integer)this.objectKeyTable[j];
      if (localInteger.intValue() == paramInt)
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return localInteger;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddInteger(paramInt);
    }
    j = this.hashIndex.linkNode(i, k);
    Integer localInteger = new Integer(paramInt);
    this.objectKeyTable[j] = localInteger;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return localInteger;
  }

  protected Long getOrAddLong(long paramLong)
  {
    int i = this.hashIndex.getHashIndex((int)(paramLong ^ paramLong >>> 32));
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      localLong = (Long)this.objectKeyTable[j];
      if (localLong.longValue() == paramLong)
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return localLong;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddLong(paramLong);
    }
    j = this.hashIndex.linkNode(i, k);
    Long localLong = new Long(paramLong);
    this.objectKeyTable[j] = localLong;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return localLong;
  }

  protected String getOrAddString(Object paramObject)
  {
    int i = this.hashIndex.getHashIndex(paramObject.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      str = (String)this.objectKeyTable[j];
      if (paramObject.equals(str))
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return str;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddString(paramObject);
    }
    String str = paramObject.toString();
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = str;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return str;
  }

  protected String getOrAddSubString(String paramString, int paramInt1, int paramInt2)
  {
    paramString = paramString.substring(paramInt1, paramInt2);
    int i = this.hashIndex.getHashIndex(paramString.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      str = (String)this.objectKeyTable[j];
      if (paramString.equals(str))
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return str;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddString(paramString);
    }
    String str = new String(paramString.toCharArray());
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = str;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return str;
  }

  protected TimestampData getOrAddDate(long paramLong)
  {
    int i = (int)paramLong ^ (int)(paramLong >>> 32);
    int j = this.hashIndex.getHashIndex(i);
    int k = this.hashIndex.hashTable[j];
    int m = -1;
    while (k >= 0)
    {
      localTimestampData = (TimestampData)this.objectKeyTable[k];
      if (localTimestampData.getSeconds() == paramLong)
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[k] = (this.accessCount++);
        return localTimestampData;
      }
      m = k;
      k = this.hashIndex.getNextLookup(k);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddDate(paramLong);
    }
    k = this.hashIndex.linkNode(j, m);
    TimestampData localTimestampData = new TimestampData(paramLong);
    this.objectKeyTable[k] = localTimestampData;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[k] = (this.accessCount++);
    return localTimestampData;
  }

  protected Double getOrAddDouble(long paramLong)
  {
    int i = this.hashIndex.getHashIndex((int)(paramLong ^ paramLong >>> 32));
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      localDouble = (Double)this.objectKeyTable[j];
      if (Double.doubleToLongBits(localDouble.doubleValue()) == paramLong)
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return localDouble;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddDouble(paramLong);
    }
    j = this.hashIndex.linkNode(i, k);
    Double localDouble = new Double(Double.longBitsToDouble(paramLong));
    this.objectKeyTable[j] = localDouble;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return localDouble;
  }

  protected Object getOrAddObject(Object paramObject)
  {
    int i = this.hashIndex.getHashIndex(paramObject.hashCode());
    int j = this.hashIndex.hashTable[i];
    int k = -1;
    while (j >= 0)
    {
      Object localObject = this.objectKeyTable[j];
      if (localObject.equals(paramObject))
      {
        if (this.accessCount > 2146435071)
          resetAccessCount();
        this.accessTable[j] = (this.accessCount++);
        return localObject;
      }
      k = j;
      j = this.hashIndex.getNextLookup(j);
    }
    if (this.hashIndex.elementCount >= this.threshold)
    {
      reset();
      return getOrAddObject(paramObject);
    }
    j = this.hashIndex.linkNode(i, k);
    this.objectKeyTable[j] = paramObject;
    if (this.accessCount > 2146435071)
      resetAccessCount();
    this.accessTable[j] = (this.accessCount++);
    return paramObject;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.store.ValuePoolHashMap
 * JD-Core Version:    0.6.2
 */