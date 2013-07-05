package org.hsqldb.store;

import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HashSet;

public class ReusableObjectCache
{
  public ReusableObjectCache()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static HashMappedList getHashMappedList()
  {
    return new HashMappedList();
  }

  public static void putHashMappedList(HashMappedList paramHashMappedList)
  {
  }

  public static HashSet getHashSet()
  {
    return new HashSet();
  }

  public static void putHashSet(HashSet paramHashSet)
  {
  }

  private void jbInit()
    throws Exception
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.store.ReusableObjectCache
 * JD-Core Version:    0.6.2
 */