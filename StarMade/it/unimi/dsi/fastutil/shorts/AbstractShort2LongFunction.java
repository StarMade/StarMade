/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractShort2LongFunction
/*     */   implements Short2LongFunction, Serializable
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
/*     */   public long put(short key, long value) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public long remove(short key) {
/*  81 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  87 */     return containsKey(((Short)ok).shortValue());
/*     */   }
/*     */ 
/*     */   public Long get(Object ok)
/*     */   {
/*  95 */     short k = ((Short)ok).shortValue();
/*  96 */     return containsKey(k) ? Long.valueOf(get(k)) : null;
/*     */   }
/*     */ 
/*     */   public Long put(Short ok, Long ov)
/*     */   {
/* 104 */     short k = ok.shortValue();
/* 105 */     boolean containsKey = containsKey(k);
/* 106 */     long v = put(k, ov.longValue());
/* 107 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Long remove(Object ok)
/*     */   {
/* 115 */     short k = ((Short)ok).shortValue();
/* 116 */     boolean containsKey = containsKey(k);
/* 117 */     long v = remove(k);
/* 118 */     return containsKey ? Long.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.AbstractShort2LongFunction
 * JD-Core Version:    0.6.2
 */