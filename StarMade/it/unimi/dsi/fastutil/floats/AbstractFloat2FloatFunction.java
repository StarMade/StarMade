/*     */ package it.unimi.dsi.fastutil.floats;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractFloat2FloatFunction
/*     */   implements Float2FloatFunction, Serializable
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
/*     */   public float put(float key, float value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public float remove(float key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Float)ok).floatValue());
/*     */   }
/*     */ 
/*     */   public Float get(Object ok)
/*     */   {
/*  95 */     float k = ((Float)ok).floatValue();
/*  96 */     return containsKey(k) ? Float.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Float put(Float ok, Float ov)
/*     */   {
/* 104 */     float k = ok.floatValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     float v = put(k, ov.floatValue());
/* 107 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Float remove(Object ok)
/*     */   {
/* 115 */     float k = ((Float)ok).floatValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     float v = remove(k);
/* 118 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.AbstractFloat2FloatFunction
 * JD-Core Version:    0.6.2
 */