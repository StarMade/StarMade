/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractChar2LongFunction
/*     */   implements Char2LongFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected long defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(long rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public long defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public long put(char key, long value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long remove(char key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Character)ok).charValue());
/*     */   }
/*     */ 
/*     */   public Long get(Object ok)
/*     */   {
/*  95 */     char k = ((Character)ok).charValue();
/*  96 */     return containsKey(k) ? Long.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Long put(Character ok, Long ov)
/*     */   {
/* 104 */     char k = ok.charValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     long v = put(k, ov.longValue());
/* 107 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Long remove(Object ok)
/*     */   {
/* 115 */     char k = ((Character)ok).charValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     long v = remove(k);
/* 118 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2LongFunction
 * JD-Core Version:    0.6.2
 */