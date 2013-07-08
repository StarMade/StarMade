/*   1:    */package com.bulletphysics.util;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Map;
/*   5:    */
/*  34:    */public class ObjectPool<T>
/*  35:    */{
/*  36:    */  private Class<T> cls;
/*  37: 37 */  private ObjectArrayList<T> list = new ObjectArrayList();
/*  38:    */  
/*  39:    */  public ObjectPool(Class<T> cls) {
/*  40: 40 */    this.cls = cls;
/*  41:    */  }
/*  42:    */  
/*  43:    */  private T create() {
/*  44:    */    try {
/*  45: 45 */      return this.cls.newInstance();
/*  46:    */    }
/*  47:    */    catch (InstantiationException e) {
/*  48: 48 */      throw new IllegalStateException(e);
/*  49:    */    }
/*  50:    */    catch (IllegalAccessException e) {
/*  51: 51 */      throw new IllegalStateException(e);
/*  52:    */    }
/*  53:    */  }
/*  54:    */  
/*  59:    */  public T get()
/*  60:    */  {
/*  61: 61 */    if (this.list.size() > 0) {
/*  62: 62 */      return this.list.remove(this.list.size() - 1);
/*  63:    */    }
/*  64:    */    
/*  65: 65 */    return create();
/*  66:    */  }
/*  67:    */  
/*  73:    */  public void release(T obj)
/*  74:    */  {
/*  75: 75 */    this.list.add(obj);
/*  76:    */  }
/*  77:    */  
/*  80: 80 */  private static ThreadLocal<Map> threadLocal = new ThreadLocal()
/*  81:    */  {
/*  82:    */    protected Map initialValue() {
/*  83: 83 */      return new HashMap();
/*  84:    */    }
/*  85:    */  };
/*  86:    */  
/*  93:    */  public static <T> ObjectPool<T> get(Class<T> cls)
/*  94:    */  {
/*  95: 95 */    Map map = (Map)threadLocal.get();
/*  96:    */    
/*  97: 97 */    ObjectPool<T> pool = (ObjectPool)map.get(cls);
/*  98: 98 */    if (pool == null) {
/*  99: 99 */      pool = new ObjectPool(cls);
/* 100:100 */      map.put(cls, pool);
/* 101:    */    }
/* 102:    */    
/* 103:103 */    return pool;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public static void cleanCurrentThread() {
/* 107:107 */    threadLocal.remove();
/* 108:    */  }
/* 109:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.util.ObjectPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */