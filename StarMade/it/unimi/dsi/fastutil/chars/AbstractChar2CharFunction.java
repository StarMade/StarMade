/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractChar2CharFunction
/*     */   implements Char2CharFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected char defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(char rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public char defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public char put(char key, char value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public char remove(char key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Character)ok).charValue());
/*     */   }
/*     */ 
/*     */   public Character get(Object ok)
/*     */   {
/*  95 */     char k = ((Character)ok).charValue();
/*  96 */     return containsKey(k) ? Character.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Character put(Character ok, Character ov)
/*     */   {
/* 104 */     char k = ok.charValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     char v = put(k, ov.charValue());
/* 107 */     return containsKey ? Character.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Character remove(Object ok)
/*     */   {
/* 115 */     char k = ((Character)ok).charValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     char v = remove(k);
/* 118 */     return containsKey ? Character.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2CharFunction
 * JD-Core Version:    0.6.2
 */