/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractInt2BooleanFunction
/*     */   implements Int2BooleanFunction, Serializable
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
/*     */   public boolean put(int key, boolean value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean remove(int key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Integer)ok).intValue());
/*     */   }
/*     */ 
/*     */   public Boolean get(Object ok)
/*     */   {
/*  95 */     int k = ((Integer)ok).intValue();
/*  96 */     return containsKey(k) ? Boolean.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Boolean put(Integer ok, Boolean ov)
/*     */   {
/* 104 */     int k = ok.intValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     boolean v = put(k, ov.booleanValue());
/* 107 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Boolean remove(Object ok)
/*     */   {
/* 115 */     int k = ((Integer)ok).intValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     boolean v = remove(k);
/* 118 */     return containsKey ? Boolean.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2BooleanFunction
 * JD-Core Version:    0.6.2
 */