/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReference2IntFunction<K>
/*     */   implements Reference2IntFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected int defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(int rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public int defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public int put(K key, int value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public int removeInt(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Integer get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Integer.valueOf(getInt(k)) : null;
/*     */   }
/*     */ 
/*     */   public Integer put(K ok, Integer ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     int v = put(k, ov.intValue());
/* 103 */     return containsKey ? Integer.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Integer remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     int v = removeInt(k);
/* 114 */     return containsKey ? Integer.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2IntFunction
 * JD-Core Version:    0.6.2
 */