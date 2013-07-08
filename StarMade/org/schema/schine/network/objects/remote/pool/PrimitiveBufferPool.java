/*   1:    */package org.schema.schine.network.objects.remote.pool;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.lang.reflect.Constructor;
/*   5:    */import java.lang.reflect.InvocationTargetException;
/*   6:    */import java.util.Map;
/*   7:    */import org.schema.schine.network.objects.remote.Streamable;
/*   8:    */
/*  20:    */public class PrimitiveBufferPool
/*  21:    */{
/*  22:    */  private Class cls;
/*  23: 23 */  private ObjectArrayList list = new ObjectArrayList();
/*  24:    */  
/*  25:    */  private Constructor constructor;
/*  26: 26 */  private static ThreadLocal threadLocal = new PrimitiveBufferPool.1();
/*  27:    */  
/*  32:    */  public static void cleanCurrentThread()
/*  33:    */  {
/*  34: 34 */    threadLocal.remove();
/*  35:    */  }
/*  36:    */  
/*  39:    */  public static PrimitiveBufferPool get(Class paramClass)
/*  40:    */  {
/*  41:    */    Map localMap;
/*  42:    */    
/*  44:    */    PrimitiveBufferPool localPrimitiveBufferPool;
/*  45:    */    
/*  47: 47 */    if ((localPrimitiveBufferPool = (PrimitiveBufferPool)(localMap = (Map)threadLocal.get()).get(paramClass)) == null) {
/*  48: 48 */      localPrimitiveBufferPool = new PrimitiveBufferPool(paramClass);
/*  49: 49 */      localMap.put(paramClass, localPrimitiveBufferPool);
/*  50:    */    }
/*  51:    */    
/*  52: 52 */    return localPrimitiveBufferPool;
/*  53:    */  }
/*  54:    */  
/*  55:    */  public PrimitiveBufferPool(Class paramClass) {
/*  56: 56 */    this.cls = paramClass;
/*  57:    */    try {
/*  58: 58 */      this.constructor = paramClass.getConstructor(new Class[] { Boolean.TYPE }); return;
/*  59: 59 */    } catch (SecurityException localSecurityException) { 
/*  60:    */      
/*  65: 65 */        localSecurityException;
/*  66:    */      
/*  67: 61 */      if (!$assertionsDisabled) throw new AssertionError();
/*  68:    */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/*  69:    */      
/*  71: 65 */        localNoSuchMethodException;
/*  72:    */      
/*  73: 64 */      if (!$assertionsDisabled) throw new AssertionError("Clazz " + paramClass);
/*  74:    */    }
/*  75:    */  }
/*  76:    */  
/*  77:    */  private Streamable create(boolean paramBoolean)
/*  78:    */  {
/*  79:    */    try
/*  80:    */    {
/*  81: 72 */      return (Streamable)this.constructor.newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
/*  82:    */    }
/*  83:    */    catch (InstantiationException paramBoolean) {
/*  84: 75 */      throw new IllegalStateException(paramBoolean);
/*  85:    */    }
/*  86:    */    catch (IllegalAccessException paramBoolean) {
/*  87: 78 */      throw new IllegalStateException(paramBoolean);
/*  88:    */    } catch (IllegalArgumentException paramBoolean) {
/*  89: 80 */      throw new IllegalStateException(paramBoolean);
/*  90:    */    } catch (InvocationTargetException paramBoolean) {
/*  91: 82 */      throw new IllegalStateException(paramBoolean);
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  99:    */  public Streamable get(boolean paramBoolean)
/* 100:    */  {
/* 101: 92 */    if (this.list.size() > 0)
/* 102:    */    {
/* 105: 96 */      return (Streamable)this.list.remove(this.list.size() - 1);
/* 106:    */    }
/* 107:    */    
/* 108: 99 */    return create(paramBoolean);
/* 109:    */  }
/* 110:    */  
/* 116:    */  public void release(Streamable paramStreamable)
/* 117:    */  {
/* 118:109 */    paramStreamable.cleanAtRelease();
/* 119:110 */    this.list.add(paramStreamable);
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */