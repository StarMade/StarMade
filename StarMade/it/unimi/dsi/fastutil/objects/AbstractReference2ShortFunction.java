/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReference2ShortFunction<K>
/*     */   implements Reference2ShortFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected short defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(short rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public short defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public short put(K key, short value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public short removeShort(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Short get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Short.valueOf(getShort(k)) : null;
/*     */   }
/*     */ 
/*     */   public Short put(K ok, Short ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     short v = put(k, ov.shortValue());
/* 103 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Short remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     short v = removeShort(k);
/* 114 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ShortFunction
 * JD-Core Version:    0.6.2
 */