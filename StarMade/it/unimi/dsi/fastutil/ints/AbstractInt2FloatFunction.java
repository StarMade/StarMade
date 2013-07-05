/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractInt2FloatFunction
/*     */   implements Int2FloatFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected float defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(float rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public float defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public float put(int key, float value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public float remove(int key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Integer)ok).intValue());
/*     */   }
/*     */ 
/*     */   public Float get(Object ok)
/*     */   {
/*  95 */     int k = ((Integer)ok).intValue();
/*  96 */     return containsKey(k) ? Float.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Float put(Integer ok, Float ov)
/*     */   {
/* 104 */     int k = ok.intValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     float v = put(k, ov.floatValue());
/* 107 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Float remove(Object ok)
/*     */   {
/* 115 */     int k = ((Integer)ok).intValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     float v = remove(k);
/* 118 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2FloatFunction
 * JD-Core Version:    0.6.2
 */