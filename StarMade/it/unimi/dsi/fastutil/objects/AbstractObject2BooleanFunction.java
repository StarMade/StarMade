/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractObject2BooleanFunction<K>
/*     */   implements Object2BooleanFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected boolean defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(boolean rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public boolean defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public boolean put(K key, boolean value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean removeBoolean(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Boolean get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Boolean.valueOf(getBoolean(k)) : null;
/*     */   }
/*     */ 
/*     */   public Boolean put(K ok, Boolean ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     boolean v = put(k, ov.booleanValue());
/* 103 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Boolean remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     boolean v = removeBoolean(k);
/* 114 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractObject2BooleanFunction
 * JD-Core Version:    0.6.2
 */