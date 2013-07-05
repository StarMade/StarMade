package org.hsqldb.lib;

import java.util.NoSuchElementException;
import org.hsqldb.store.BaseHashMap;

public class IntKeyLongValueHashMap extends BaseHashMap
{
  public IntKeyLongValueHashMap()
  {
    this(8);
  }

  public IntKeyLongValueHashMap(int paramInt)
    throws IllegalArgumentException
  {
    super(paramInt, 1, 2, false);
  }

  public long get(int paramInt)
    throws NoSuchElementException
  {
    int i = getLookup(paramInt);
    if (i != -1)
      return this.longValueTable[i];
    throw new NoSuchElementException();
  }

  public long get(int paramInt1, int paramInt2)
  {
    int i = getLookup(paramInt1);
    if (i != -1)
      return this.longValueTable[i];
    return paramInt2;
  }

  public boolean get(int paramInt, long[] paramArrayOfLong)
  {
    int i = getLookup(paramInt);
    if (i != -1)
    {
      paramArrayOfLong[0] = this.longValueTable[i];
      return true;
    }
    return false;
  }

  public boolean put(int paramInt1, int paramInt2)
  {
    int i = size();
    super.addOrRemove(paramInt1, paramInt2, null, null, false);
    return i != size();
  }

  public boolean remove(int paramInt)
  {
    int i = size();
    super.addOrRemove(paramInt, 0L, null, null, true);
    return i != size();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.IntKeyLongValueHashMap
 * JD-Core Version:    0.6.2
 */