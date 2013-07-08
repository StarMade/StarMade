package org.schema.schine.network.objects.remote.pool;

import com.bulletphysics.util.ObjectArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.schema.schine.network.objects.remote.StreamableArray;

public class ArrayBufferPool
{
  private Class cls;
  private ObjectArrayList list = new ObjectArrayList();
  private Constructor constructor;
  private Integer size;
  private static ThreadLocal threadLocal = new ArrayBufferPool.1();
  
  public static void cleanCurrentThread()
  {
    threadLocal.remove();
  }
  
  public static ArrayBufferPool get(Class paramClass, Integer paramInteger)
  {
    Object localObject1;
    Object localObject2;
    if ((localObject2 = (Map)(localObject1 = (Map)threadLocal.get()).get(paramClass)) == null)
    {
      localObject2 = new HashMap();
      ((Map)localObject1).put(paramClass, localObject2);
    }
    if ((localObject1 = (ArrayBufferPool)((Map)localObject2).get(paramInteger)) == null)
    {
      (localObject1 = new ArrayBufferPool(paramClass)).size = paramInteger;
      ((Map)localObject2).put(paramInteger, localObject1);
    }
    return localObject1;
  }
  
  public ArrayBufferPool(Class paramClass)
  {
    this.cls = paramClass;
    try
    {
      this.constructor = paramClass.getConstructor(new Class[] { Integer.TYPE, Boolean.TYPE });
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
        throw new AssertionError();
      }
    }
  }
  
  private StreamableArray create(Integer paramInteger, boolean paramBoolean)
  {
    try
    {
      return (StreamableArray)this.constructor.newInstance(new Object[] { paramInteger, Boolean.valueOf(paramBoolean) });
    }
    catch (InstantiationException paramInteger)
    {
      throw new IllegalStateException(paramInteger);
    }
    catch (IllegalAccessException paramInteger)
    {
      throw new IllegalStateException(paramInteger);
    }
    catch (IllegalArgumentException paramInteger)
    {
      throw new IllegalStateException(paramInteger);
    }
    catch (InvocationTargetException paramInteger)
    {
      throw new IllegalStateException(paramInteger);
    }
  }
  
  public StreamableArray get(boolean paramBoolean)
  {
    if (this.list.size() > 0) {
      return (StreamableArray)this.list.remove(this.list.size() - 1);
    }
    return create(this.size, paramBoolean);
  }
  
  public void release(StreamableArray paramStreamableArray)
  {
    assert (paramStreamableArray.arrayLength() == this.size.intValue()) : (paramStreamableArray.arrayLength() + " / " + this.size);
    paramStreamableArray.cleanAtRelease();
    this.list.add(paramStreamableArray);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.ArrayBufferPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */