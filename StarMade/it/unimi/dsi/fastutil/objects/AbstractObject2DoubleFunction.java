/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractObject2DoubleFunction<K>
/*     */   implements Object2DoubleFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected double defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(double rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public double defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public double put(K key, double value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double removeDouble(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Double get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Double.valueOf(getDouble(k)) : null;
/*     */   }
/*     */ 
/*     */   public Double put(K ok, Double ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     double v = put(k, ov.doubleValue());
/* 103 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Double remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     double v = removeDouble(k);
/* 114 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2DoubleFunction
 * JD-Core Version:    0.6.2
 */