/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReference2FloatFunction<K>
/*     */   implements Reference2FloatFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected float defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(float rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public float defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public float put(K key, float value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public float removeFloat(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Float get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Float.valueOf(getFloat(k)) : null;
/*     */   }
/*     */ 
/*     */   public Float put(K ok, Float ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     float v = put(k, ov.floatValue());
/* 103 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Float remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     float v = removeFloat(k);
/* 114 */     return containsKey ? Float.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2FloatFunction
 * JD-Core Version:    0.6.2
 */