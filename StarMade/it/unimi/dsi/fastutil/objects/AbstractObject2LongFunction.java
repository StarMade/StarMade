/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractObject2LongFunction<K>
/*     */   implements Object2LongFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected long defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(long rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public long defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public long put(K key, long value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long removeLong(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Long get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Long.valueOf(getLong(k)) : null;
/*     */   }
/*     */ 
/*     */   public Long put(K ok, Long ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     long v = put(k, ov.longValue());
/* 103 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Long remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     long v = removeLong(k);
/* 114 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2LongFunction
 * JD-Core Version:    0.6.2
 */