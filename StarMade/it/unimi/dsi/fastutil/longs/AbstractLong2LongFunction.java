/*     */ package it.unimi.dsi.fastutil.longs;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractLong2LongFunction
/*     */   implements Long2LongFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected long defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(long rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public long defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public long put(long key, long value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long remove(long key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Long)ok).longValue());
/*     */   }
/*     */ 
/*     */   public Long get(Object ok)
/*     */   {
/*  95 */     long k = ((Long)ok).longValue();
/*  96 */     return containsKey(k) ? Long.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Long put(Long ok, Long ov)
/*     */   {
/* 104 */     long k = ok.longValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     long v = put(k, ov.longValue());
/* 107 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Long remove(Object ok)
/*     */   {
/* 115 */     long k = ((Long)ok).longValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     long v = remove(k);
/* 118 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.AbstractLong2LongFunction
 * JD-Core Version:    0.6.2
 */