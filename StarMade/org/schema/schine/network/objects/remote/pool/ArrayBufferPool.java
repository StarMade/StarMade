/*     */ package org.schema.schine.network.objects.remote.pool;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.schema.schine.network.objects.remote.StreamableArray;
/*     */ 
/*     */ public class ArrayBufferPool
/*     */ {
/*     */   private Class cls;
/*  26 */   private ObjectArrayList list = new ObjectArrayList();
/*     */   private Constructor constructor;
/*     */   private Integer size;
/*  30 */   private static ThreadLocal threadLocal = new ArrayBufferPool.1();
/*     */ 
/*     */   public static void cleanCurrentThread()
/*     */   {
/*  39 */     threadLocal.remove();
/*     */   }
/*     */ 
/*     */   public static ArrayBufferPool get(Class paramClass, Integer paramInteger)
/*     */   {
/*     */     Object localObject1;
/*     */     Object localObject2;
/*  54 */     if ((
/*  54 */       localObject2 = (Map)(
/*  52 */       localObject1 = (Map)threadLocal.get())
/*  52 */       .get(paramClass)) == null)
/*     */     {
/*  55 */       localObject2 = new HashMap();
/*  56 */       ((Map)localObject1).put(paramClass, localObject2);
/*     */     }
/*     */ 
/*  60 */     if ((
/*  60 */       localObject1 = (ArrayBufferPool)((Map)localObject2).get(paramInteger)) == null)
/*     */     {
/*  61 */       (
/*  62 */         localObject1 = new ArrayBufferPool(paramClass)).size = 
/*  62 */         paramInteger;
/*     */ 
/*  64 */       ((Map)localObject2).put(paramInteger, localObject1);
/*     */     }
/*     */ 
/*  67 */     return localObject1;
/*     */   }
/*     */ 
/*     */   public ArrayBufferPool(Class paramClass) {
/*  71 */     this.cls = paramClass;
/*     */     try
/*     */     {
/*  73 */       this.constructor = paramClass.getConstructor(new Class[] { Integer.TYPE, Boolean.TYPE });
/*     */       return;
/*     */     }
/*     */     catch (SecurityException localSecurityException)
/*     */     {
/*  80 */       localSecurityException.printStackTrace();
/*     */ 
/*  76 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException)
/*     */     {
/*  80 */       localNoSuchMethodException.printStackTrace();
/*     */ 
/*  79 */       if (!$assertionsDisabled) throw new AssertionError();
/*     */     }
/*     */   }
/*     */ 
/*     */   private StreamableArray create(Integer paramInteger, boolean paramBoolean)
/*     */   {
/*     */     try
/*     */     {
/*  87 */       return (StreamableArray)this.constructor.newInstance(new Object[] { paramInteger, Boolean.valueOf(paramBoolean) });
/*     */     }
/*     */     catch (InstantiationException paramInteger) {
/*  90 */       throw new IllegalStateException(paramInteger);
/*     */     }
/*     */     catch (IllegalAccessException paramInteger) {
/*  93 */       throw new IllegalStateException(paramInteger);
/*     */     } catch (IllegalArgumentException paramInteger) {
/*  95 */       throw new IllegalStateException(paramInteger); } catch (InvocationTargetException paramInteger) {
/*     */     }
/*  97 */     throw new IllegalStateException(paramInteger);
/*     */   }
/*     */ 
/*     */   public StreamableArray get(boolean paramBoolean)
/*     */   {
/* 107 */     if (this.list.size() > 0)
/*     */     {
/* 110 */       return (StreamableArray)this.list.remove(this.list.size() - 1);
/*     */     }
/*     */ 
/* 114 */     return create(this.size, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void release(StreamableArray paramStreamableArray)
/*     */   {
/* 124 */     assert (paramStreamableArray.arrayLength() == this.size.intValue()) : (paramStreamableArray.arrayLength() + " / " + this.size);
/*     */ 
/* 126 */     paramStreamableArray.cleanAtRelease();
/* 127 */     this.list.add(paramStreamableArray);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.ArrayBufferPool
 * JD-Core Version:    0.6.2
 */