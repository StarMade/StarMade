/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractDouble2IntFunction
/*     */   implements Double2IntFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected int defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(int rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public int defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public int put(double key, int value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public int remove(double key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Integer get(Object ok)
/*     */   {
/*  95 */     double k = ((Double)ok).doubleValue();
/*  96 */     return containsKey(k) ? Integer.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Integer put(Double ok, Integer ov)
/*     */   {
/* 104 */     double k = ok.doubleValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     int v = put(k, ov.intValue());
/* 107 */     return containsKey ? Integer.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Integer remove(Object ok)
/*     */   {
/* 115 */     double k = ((Double)ok).doubleValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     int v = remove(k);
/* 118 */     return containsKey ? Integer.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2IntFunction
 * JD-Core Version:    0.6.2
 */