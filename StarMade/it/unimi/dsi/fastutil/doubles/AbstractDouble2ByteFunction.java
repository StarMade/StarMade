/*     */ package it.unimi.dsi.fastutil.doubles;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractDouble2ByteFunction
/*     */   implements Double2ByteFunction, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected byte defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(byte rv)
/*     */   {
/*  72 */     this.defRetValue = rv;
/*     */   }
/*     */   public byte defaultReturnValue() {
/*  75 */     return this.defRetValue;
/*     */   }
/*     */   public byte put(double key, byte value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte remove(double key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Double)ok).doubleValue());
/*     */   }
/*     */ 
/*     */   public Byte get(Object ok)
/*     */   {
/*  95 */     double k = ((Double)ok).doubleValue();
/*  96 */     return containsKey(k) ? Byte.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Byte put(Double ok, Byte ov)
/*     */   {
/* 104 */     double k = ok.doubleValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     byte v = put(k, ov.byteValue());
/* 107 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Byte remove(Object ok)
/*     */   {
/* 115 */     double k = ((Double)ok).doubleValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     byte v = remove(k);
/* 118 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.AbstractDouble2ByteFunction
 * JD-Core Version:    0.6.2
 */