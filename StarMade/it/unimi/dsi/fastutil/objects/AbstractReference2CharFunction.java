/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReference2CharFunction<K>
/*     */   implements Reference2CharFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected char defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(char rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public char defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public char put(K key, char value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public char removeChar(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Character get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Character.valueOf(getChar(k)) : null;
/*     */   }
/*     */ 
/*     */   public Character put(K ok, Character ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     char v = put(k, ov.charValue());
/* 103 */     return containsKey ? Character.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Character remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     char v = removeChar(k);
/* 114 */     return containsKey ? Character.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2CharFunction
 * JD-Core Version:    0.6.2
 */