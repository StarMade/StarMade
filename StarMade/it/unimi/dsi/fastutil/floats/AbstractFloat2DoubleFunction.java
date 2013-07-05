/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractFloat2DoubleFunction
/*     */   implements Float2DoubleFunction, Serializable
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
/*     */   public double put(float key, double value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double remove(float key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Float)ok).floatValue());
/*     */   }
/*     */ 
/*     */   public Double get(Object ok)
/*     */   {
/*  95 */     float k = ((Float)ok).floatValue();
/*  96 */     return containsKey(k) ? Double.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Double put(Float ok, Double ov)
/*     */   {
/* 104 */     float k = ok.floatValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     double v = put(k, ov.doubleValue());
/* 107 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Double remove(Object ok)
/*     */   {
/* 115 */     float k = ((Float)ok).floatValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     double v = remove(k);
/* 118 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2DoubleFunction
 * JD-Core Version:    0.6.2
 */