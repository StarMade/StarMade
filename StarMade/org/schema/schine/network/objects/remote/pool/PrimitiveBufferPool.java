package org.schema.schine.network.objects.remote.pool;

import com.bulletphysics.util.ObjectArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.schema.schine.network.objects.remote.Streamable;

public class PrimitiveBufferPool
{
  private Class cls;
  private ObjectArrayList list = new ObjectArrayList();
  private Constructor constructor;
  private static ThreadLocal threadLocal = new PrimitiveBufferPool.1();
  
  public static void cleanCurrentThread()
  {
    threadLocal.remove();
  }
  
  public static PrimitiveBufferPool get(Class paramClass)
  {
    Map localMap;
    PrimitiveBufferPool localPrimitiveBufferPool;
    if ((localPrimitiveBufferPool = (PrimitiveBufferPool)(localMap = (Map)threadLocal.get()).get(paramClass)) == null)
    {
      localPrimitiveBufferPool = new PrimitiveBufferPool(paramClass);
      localMap.put(paramClass, localPrimitiveBufferPool);
    }
    return localPrimitiveBufferPool;
  }
  
  public PrimitiveBufferPool(Class paramClass)
  {
    this.cls = paramClass;
    try
    {
      this.constructor = paramClass.getConstructor(new Class[] { Boolean.TYPE });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException;
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException;
      if (!$assertionsDisabled) {
        throw new AssertionError("Clazz " + paramClass);
      }
    }
  }
  
  private Streamable create(boolean paramBoolean)
  {
    try
    {
      return (Streamable)this.constructor.newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
    }
    catch (InstantiationException paramBoolean)
    {
      throw new IllegalStateException(paramBoolean);
    }
    catch (IllegalAccessException paramBoolean)
    {
      throw new IllegalStateException(paramBoolean);
    }
    catch (IllegalArgumentException paramBoolean)
    {
      throw new IllegalStateException(paramBoolean);
    }
    catch (InvocationTargetException paramBoolean)
    {
      throw new IllegalStateException(paramBoolean);
    }
  }
  
  public Streamable get(boolean paramBoolean)
  {
    if (this.list.size() > 0) {
      return (Streamable)this.list.remove(this.list.size() - 1);
    }
    return create(paramBoolean);
  }
  
  public void release(Streamable paramStreamable)
  {
    paramStreamable.cleanAtRelease();
    this.list.add(paramStreamable);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */