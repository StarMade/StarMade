/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractChar2BooleanFunction
/*     */   implements Char2BooleanFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected boolean defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(boolean rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public boolean defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public boolean put(char key, boolean value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean remove(char key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Character)ok).charValue());
/*     */   }
/*     */ 
/*     */   public Boolean get(Object ok)
/*     */   {
/*  95 */     char k = ((Character)ok).charValue();
/*  96 */     return containsKey(k) ? Boolean.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Boolean put(Character ok, Boolean ov)
/*     */   {
/* 104 */     char k = ok.charValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     boolean v = put(k, ov.booleanValue());
/* 107 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Boolean remove(Object ok)
/*     */   {
/* 115 */     char k = ((Character)ok).charValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     boolean v = remove(k);
/* 118 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2BooleanFunction
 * JD-Core Version:    0.6.2
 */