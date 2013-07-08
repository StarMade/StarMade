/*   1:    */package org.schema.schine.network.objects.remote.pool;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ObjectArrayList;
/*   4:    */import java.lang.reflect.Constructor;
/*   5:    */import java.lang.reflect.InvocationTargetException;
/*   6:    */import java.util.HashMap;
/*   7:    */import java.util.Map;
/*   8:    */import org.schema.schine.network.objects.remote.StreamableArray;
/*   9:    */
/*  23:    */public class ArrayBufferPool
/*  24:    */{
/*  25:    */  private Class cls;
/*  26: 26 */  private ObjectArrayList list = new ObjectArrayList();
/*  27:    */  
/*  28:    */  private Constructor constructor;
/*  29:    */  private Integer size;
/*  30: 30 */  private static ThreadLocal threadLocal = new ArrayBufferPool.1();
/*  31:    */  
/*  37:    */  public static void cleanCurrentThread()
/*  38:    */  {
/*  39: 39 */    threadLocal.remove();
/*  40:    */  }
/*  41:    */  
/*  45:    */  public static ArrayBufferPool get(Class paramClass, Integer paramInteger)
/*  46:    */  {
/*  47:    */    Object localObject1;
/*  48:    */    
/*  51:    */    Object localObject2;
/*  52:    */    
/*  54: 54 */    if ((localObject2 = (Map)(localObject1 = (Map)threadLocal.get()).get(paramClass)) == null) {
/*  55: 55 */      localObject2 = new HashMap();
/*  56: 56 */      ((Map)localObject1).put(paramClass, localObject2);
/*  57:    */    }
/*  58:    */    
/*  60: 60 */    if ((localObject1 = (ArrayBufferPool)((Map)localObject2).get(paramInteger)) == null)
/*  61:    */    {
/*  62: 62 */      (localObject1 = new ArrayBufferPool(paramClass)).size = paramInteger;
/*  63:    */      
/*  64: 64 */      ((Map)localObject2).put(paramInteger, localObject1);
/*  65:    */    }
/*  66:    */    
/*  67: 67 */    return localObject1;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public ArrayBufferPool(Class paramClass) {
/*  71: 71 */    this.cls = paramClass;
/*  72:    */    try {
/*  73: 73 */      this.constructor = paramClass.getConstructor(new Class[] { Integer.TYPE, Boolean.TYPE }); return;
/*  74: 74 */    } catch (SecurityException localSecurityException) { 
/*  75:    */      
/*  80: 80 */        localSecurityException;
/*  81:    */      
/*  82: 76 */      if (!$assertionsDisabled) throw new AssertionError();
/*  83:    */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/*  84:    */      
/*  86: 80 */        localNoSuchMethodException;
/*  87:    */      
/*  88: 79 */      if (!$assertionsDisabled) throw new AssertionError();
/*  89:    */    }
/*  90:    */  }
/*  91:    */  
/*  92:    */  private StreamableArray create(Integer paramInteger, boolean paramBoolean)
/*  93:    */  {
/*  94:    */    try
/*  95:    */    {
/*  96: 87 */      return (StreamableArray)this.constructor.newInstance(new Object[] { paramInteger, Boolean.valueOf(paramBoolean) });
/*  97:    */    }
/*  98:    */    catch (InstantiationException paramInteger) {
/*  99: 90 */      throw new IllegalStateException(paramInteger);
/* 100:    */    }
/* 101:    */    catch (IllegalAccessException paramInteger) {
/* 102: 93 */      throw new IllegalStateException(paramInteger);
/* 103:    */    } catch (IllegalArgumentException paramInteger) {
/* 104: 95 */      throw new IllegalStateException(paramInteger);
/* 105:    */    } catch (InvocationTargetException paramInteger) {
/* 106: 97 */      throw new IllegalStateException(paramInteger);
/* 107:    */    }
/* 108:    */  }
/* 109:    */  
/* 114:    */  public StreamableArray get(boolean paramBoolean)
/* 115:    */  {
/* 116:107 */    if (this.list.size() > 0)
/* 117:    */    {
/* 119:110 */      return (StreamableArray)this.list.remove(this.list.size() - 1);
/* 120:    */    }
/* 121:    */    
/* 123:114 */    return create(this.size, paramBoolean);
/* 124:    */  }
/* 125:    */  
/* 131:    */  public void release(StreamableArray paramStreamableArray)
/* 132:    */  {
/* 133:124 */    assert (paramStreamableArray.arrayLength() == this.size.intValue()) : (paramStreamableArray.arrayLength() + " / " + this.size);
/* 134:    */    
/* 135:126 */    paramStreamableArray.cleanAtRelease();
/* 136:127 */    this.list.add(paramStreamableArray);
/* 137:    */  }
/* 138:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.ArrayBufferPool
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */