/*     */ package it.unimi.dsi.fastutil.ints;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractInt2ByteFunction
/*     */   implements Int2ByteFunction, Serializable
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
/*     */   public byte put(int key, byte value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte remove(int key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Integer)ok).intValue());
/*     */   }
/*     */ 
/*     */   public Byte get(Object ok)
/*     */   {
/*  95 */     int k = ((Integer)ok).intValue();
/*  96 */     return containsKey(k) ? Byte.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Byte put(Integer ok, Byte ov)
/*     */   {
/* 104 */     int k = ok.intValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     byte v = put(k, ov.byteValue());
/* 107 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Byte remove(Object ok)
/*     */   {
/* 115 */     int k = ((Integer)ok).intValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     byte v = remove(k);
/* 118 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.AbstractInt2ByteFunction
 * JD-Core Version:    0.6.2
 */