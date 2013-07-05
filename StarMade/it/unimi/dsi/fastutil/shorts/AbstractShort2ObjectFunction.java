/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractShort2ObjectFunction<V>
/*     */   implements Short2ObjectFunction<V>, Serializable
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
/*     */   public V put(short key, V value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public V remove(short key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  86 */     return containsKey(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public V get(Object ok)
/*     */   {
/*  94 */     short k = ((Short)ok).shortValue();
/*  95 */     return containsKey(k) ? get(k) : null;
/*     */   }
/*     */ 
/*     */   public V put(Short ok, V ov)
/*     */   {
/* 103 */     short k = ok.shortValue();
/* 104 */     boolean containsKey = containsKey(k);
/* 105 */     Object v = put(k, ov);
/* 106 */     return containsKey ? v : null;
/*     */   }
/*     */ 
/*     */   public V remove(Object ok)
/*     */   {
/* 114 */     short k = ((Short)ok).shortValue();
/* 115 */     boolean containsKey = containsKey(k);
/* 116 */     Object v = remove(k);
/* 117 */     return containsKey ? v : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ObjectFunction
 * JD-Core Version:    0.6.2
 */