/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractChar2DoubleFunction
/*     */   implements Char2DoubleFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected double defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(double rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public double defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public double put(char key, double value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public double remove(char key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Character)ok).charValue());
/*     */   }
/*     */ 
/*     */   public Double get(Object ok)
/*     */   {
/*  95 */     char k = ((Character)ok).charValue();
/*  96 */     return containsKey(k) ? Double.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Double put(Character ok, Double ov)
/*     */   {
/* 104 */     char k = ok.charValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     double v = put(k, ov.doubleValue());
/* 107 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Double remove(Object ok)
/*     */   {
/* 115 */     char k = ((Character)ok).charValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     double v = remove(k);
/* 118 */     return containsKey ? Double.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2DoubleFunction
 * JD-Core Version:    0.6.2
 */