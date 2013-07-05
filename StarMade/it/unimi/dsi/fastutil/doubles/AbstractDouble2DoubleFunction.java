/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractDouble2DoubleFunction
/*     */   implements Double2DoubleFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected double defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(double rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public double defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public double put(double key, double value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double remove(double key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Double get(Object ok)
/*     */   {
/*  95 */     double k = ((Double)ok).doubleValue();
/*  96 */     return containsKey(k) ? Double.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Double put(Double ok, Double ov)
/*     */   {
/* 104 */     double k = ok.doubleValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     double v = put(k, ov.doubleValue());
/* 107 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Double remove(Object ok)
/*     */   {
/* 115 */     double k = ((Double)ok).doubleValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     double v = remove(k);
/* 118 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2DoubleFunction
 * JD-Core Version:    0.6.2
 */