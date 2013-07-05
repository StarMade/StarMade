/*     */ package org.schema.schine.network.objects.remote.pool;
/*     */ 
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Map;
/*     */ import org.schema.schine.network.objects.remote.Streamable;
/*     */ 
/*     */ public class PrimitiveBufferPool
/*     */ {
/*     */   private Class cls;
/*  23 */   private ObjectArrayList list = new ObjectArrayList();
/*     */   private Constructor constructor;
/*  26 */   private static ThreadLocal threadLocal = new PrimitiveBufferPool.1();
/*     */ 
/*     */   public static void cleanCurrentThread()
/*     */   {
/*  34 */     threadLocal.remove();
/*     */   }
/*     */ 
/*     */   public static PrimitiveBufferPool get(Class paramClass)
/*     */   {
/*     */     Map localMap;
/*     */     PrimitiveBufferPool localPrimitiveBufferPool;
/*  47 */     if ((
/*  47 */       localPrimitiveBufferPool = (PrimitiveBufferPool)(
/*  46 */       localMap = (Map)threadLocal.get())
/*  46 */       .get(paramClass)) == null)
/*     */     {
/*  48 */       localPrimitiveBufferPool = new PrimitiveBufferPool(paramClass);
/*  49 */       localMap.put(paramClass, localPrimitiveBufferPool);
/*     */     }
/*     */ 
/*  52 */     return localPrimitiveBufferPool;
/*     */   }
/*     */ 
/*     */   public PrimitiveBufferPool(Class paramClass) {
/*  56 */     this.cls = paramClass;
/*     */     try
/*     */     {
/*  58 */       this.constructor = paramClass.getConstructor(new Class[] { Boolean.TYPE });
/*     */       return;
/*     */     }
/*     */     catch (SecurityException localSecurityException)
/*     */     {
/*  65 */       localSecurityException.printStackTrace();
/*     */ 
/*  61 */       if (!$assertionsDisabled) throw new AssertionError(); return;
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException)
/*     */     {
/*  65 */       localNoSuchMethodException.printStackTrace();
/*     */ 
/*  64 */       if (!$assertionsDisabled) throw new AssertionError("Clazz " + paramClass);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Streamable create(boolean paramBoolean)
/*     */   {
/*     */     try
/*     */     {
/*  72 */       return (Streamable)this.constructor.newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
/*     */     }
/*     */     catch (InstantiationException paramBoolean) {
/*  75 */       throw new IllegalStateException(paramBoolean);
/*     */     }
/*     */     catch (IllegalAccessException paramBoolean) {
/*  78 */       throw new IllegalStateException(paramBoolean);
/*     */     } catch (IllegalArgumentException paramBoolean) {
/*  80 */       throw new IllegalStateException(paramBoolean); } catch (InvocationTargetException paramBoolean) {
/*     */     }
/*  82 */     throw new IllegalStateException(paramBoolean);
/*     */   }
/*     */ 
/*     */   public Streamable get(boolean paramBoolean)
/*     */   {
/*  92 */     if (this.list.size() > 0)
/*     */     {
/*  96 */       return (Streamable)this.list.remove(this.list.size() - 1);
/*     */     }
/*     */ 
/*  99 */     return create(paramBoolean);
/*     */   }
/*     */ 
/*     */   public void release(Streamable paramStreamable)
/*     */   {
/* 109 */     paramStreamable.cleanAtRelease();
/* 110 */     this.list.add(paramStreamable);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.pool.PrimitiveBufferPool
 * JD-Core Version:    0.6.2
 */