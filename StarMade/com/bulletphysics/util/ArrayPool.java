package com.bulletphysics.util;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ArrayPool<T>
{
  private Class componentType;
  private ObjectArrayList list = new ObjectArrayList();
  private Comparator comparator;
  private IntValue key = new IntValue(null);
  private static Comparator floatComparator = new Comparator()
  {
    public int compare(Object local_o1, Object local_o2)
    {
      int len1 = (local_o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o1).value : ((float[])local_o1).length;
      int len2 = (local_o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o2).value : ((float[])local_o2).length;
      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
    }
  };
  private static Comparator intComparator = new Comparator()
  {
    public int compare(Object local_o1, Object local_o2)
    {
      int len1 = (local_o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o1).value : ((int[])local_o1).length;
      int len2 = (local_o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o2).value : ((int[])local_o2).length;
      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
    }
  };
  private static Comparator objectComparator = new Comparator()
  {
    public int compare(Object local_o1, Object local_o2)
    {
      int len1 = (local_o1 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o1).value : ((Object[])local_o1).length;
      int len2 = (local_o2 instanceof ArrayPool.IntValue) ? ((ArrayPool.IntValue)local_o2).value : ((Object[])local_o2).length;
      return len1 < len2 ? -1 : len1 > len2 ? 1 : 0;
    }
  };
  private static ThreadLocal<Map> threadLocal = new ThreadLocal()
  {
    protected Map initialValue()
    {
      return new HashMap();
    }
  };
  
  public ArrayPool(Class componentType)
  {
    this.componentType = componentType;
    if (componentType == Float.TYPE) {
      this.comparator = floatComparator;
    } else if (componentType == Integer.TYPE) {
      this.comparator = intComparator;
    } else if (!componentType.isPrimitive()) {
      this.comparator = objectComparator;
    } else {
      throw new UnsupportedOperationException("unsupported type " + componentType);
    }
  }
  
  private T create(int length)
  {
    return Array.newInstance(this.componentType, length);
  }
  
  public T getFixed(int length)
  {
    this.key.value = length;
    int index = Collections.binarySearch(this.list, this.key, this.comparator);
    if (index < 0) {
      return create(length);
    }
    return this.list.remove(index);
  }
  
  public T getAtLeast(int length)
  {
    this.key.value = length;
    int index = Collections.binarySearch(this.list, this.key, this.comparator);
    if (index < 0)
    {
      index = -index - 1;
      if (index < this.list.size()) {
        return this.list.remove(index);
      }
      return create(length);
    }
    return this.list.remove(index);
  }
  
  public void release(T array)
  {
    int index = Collections.binarySearch(this.list, array, this.comparator);
    if (index < 0) {
      index = -index - 1;
    }
    this.list.add(index, array);
    if (this.comparator == objectComparator)
    {
      Object[] objArray = (Object[])array;
      for (int local_i = 0; local_i < objArray.length; local_i++) {
        objArray[local_i] = null;
      }
    }
  }
  
  public static <T> ArrayPool<T> get(Class cls)
  {
    Map map = (Map)threadLocal.get();
    ArrayPool<T> pool = (ArrayPool)map.get(cls);
    if (pool == null)
    {
      pool = new ArrayPool(cls);
      map.put(cls, pool);
    }
    return pool;
  }
  
  public static void cleanCurrentThread()
  {
    threadLocal.remove();
  }
  
  private static class IntValue
  {
    public int value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.ArrayPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */