/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractDouble2LongFunction
/*     */   implements Double2LongFunction, Serializable
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
/*     */   public long put(double key, long value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long remove(double key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Long get(Object ok)
/*     */   {
/*  95 */     double k = ((Double)ok).doubleValue();
/*  96 */     return containsKey(k) ? Long.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Long put(Double ok, Long ov)
/*     */   {
/* 104 */     double k = ok.doubleValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     long v = put(k, ov.longValue());
/* 107 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Long remove(Object ok)
/*     */   {
/* 115 */     double k = ((Double)ok).doubleValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     long v = remove(k);
/* 118 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2LongFunction
 * JD-Core Version:    0.6.2
 */