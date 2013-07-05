/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractInt2ReferenceFunction<V>
/*     */   implements Int2ReferenceFunction<V>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected V defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(V rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public V defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public V put(int key, V value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public V remove(int key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  86 */     return containsKey(((Integer)ok).intValue());
/*     */   }
/*     */ 
/*     */   public V get(Object ok)
/*     */   {
/*  94 */     int k = ((Integer)ok).intValue();
/*  95 */     return containsKey(k) ? get(k) : null;
/*     */   }
/*     */ 
/*     */   public V put(Integer ok, V ov)
/*     */   {
/* 103 */     int k = ok.intValue();
/* 104 */     boolean containsKey = containsKey(k);
/* 105 */     Object v = put(k, ov);
/* 106 */     return containsKey ? v : null;
/*     */   }
/*     */ 
/*     */   public V remove(Object ok)
/*     */   {
/* 114 */     int k = ((Integer)ok).intValue();
/* 115 */     boolean containsKey = containsKey(k);
/* 116 */     Object v = remove(k);
/* 117 */     return containsKey ? v : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ReferenceFunction
 * JD-Core Version:    0.6.2
 */