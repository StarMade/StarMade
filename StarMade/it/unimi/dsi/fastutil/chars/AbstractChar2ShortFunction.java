/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractChar2ShortFunction
/*     */   implements Char2ShortFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected short defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(short rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public short defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public short put(char key, short value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public short remove(char key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Character)ok).charValue());
/*     */   }
/*     */ 
/*     */   public Short get(Object ok)
/*     */   {
/*  95 */     char k = ((Character)ok).charValue();
/*  96 */     return containsKey(k) ? Short.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Short put(Character ok, Short ov)
/*     */   {
/* 104 */     char k = ok.charValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     short v = put(k, ov.shortValue());
/* 107 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Short remove(Object ok)
/*     */   {
/* 115 */     char k = ((Character)ok).charValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     short v = remove(k);
/* 118 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ShortFunction
 * JD-Core Version:    0.6.2
 */