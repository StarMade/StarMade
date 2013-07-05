/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractShort2ShortFunction
/*     */   implements Short2ShortFunction, Serializable
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
/*     */   public short put(short key, short value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public short remove(short key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public Short get(Object ok)
/*     */   {
/*  95 */     short k = ((Short)ok).shortValue();
/*  96 */     return containsKey(k) ? Short.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Short put(Short ok, Short ov)
/*     */   {
/* 104 */     short k = ok.shortValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     short v = put(k, ov.shortValue());
/* 107 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Short remove(Object ok)
/*     */   {
/* 115 */     short k = ((Short)ok).shortValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     short v = remove(k);
/* 118 */     return containsKey ? Short.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2ShortFunction
 * JD-Core Version:    0.6.2
 */