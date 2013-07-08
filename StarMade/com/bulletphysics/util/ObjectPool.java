package com.bulletphysics.util;

import java.util.HashMap;
import java.util.Map;

public class ObjectPool<T>
{
  private Class<T> cls;
  private ObjectArrayList<T> list = new ObjectArrayList();
  private static ThreadLocal<Map> threadLocal = new ThreadLocal()
  {
    protected Map initialValue()
    {
      return new HashMap();
    }
  };
  
  public ObjectPool(Class<T> cls)
  {
    this.cls = cls;
  }
  
  private T create()
  {
    try
    {
      return this.cls.newInstance();
    }
    catch (InstantiationException local_e)
    {
      throw new IllegalStateException(local_e);
    }
    catch (IllegalAccessException local_e)
    {
      throw new IllegalStateException(local_e);
    }
  }
  
  public T get()
  {
    if (this.list.size() > 0) {
      return this.list.remove(this.list.size() - 1);
    }
    return create();
  }
  
  public void release(T obj)
  {
    this.list.add(obj);
  }
  
  public static <T> ObjectPool<T> get(Class<T> cls)
  {
    Map map = (Map)threadLocal.get();
    ObjectPool<T> pool = (ObjectPool)map.get(cls);
    if (pool == null)
    {
      pool = new ObjectPool(cls);
      map.put(cls, pool);
    }
    return pool;
  }
  
  public static void cleanCurrentThread()
  {
    threadLocal.remove();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.ObjectPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */