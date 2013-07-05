/*     */ package it.unimi.dsi.fastutil.objects;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractReference2ByteFunction<K>
/*     */   implements Reference2ByteFunction<K>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected byte defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(byte rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public byte defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public byte put(K key, byte value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public byte removeByte(Object key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public Byte get(Object ok)
/*     */   {
/*  91 */     Object k = ok;
/*  92 */     return containsKey(k) ? Byte.valueOf(getByte(k)) : null;
/*     */   }
/*     */ 
/*     */   public Byte put(K ok, Byte ov)
/*     */   {
/* 100 */     Object k = ok;
/* 101 */     boolean containsKey = containsKey(k);
/* 102 */     byte v = put(k, ov.byteValue());
/* 103 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ 
/*     */   public Byte remove(Object ok)
/*     */   {
/* 111 */     Object k = ok;
/* 112 */     boolean containsKey = containsKey(k);
/* 113 */     byte v = removeByte(k);
/* 114 */     return containsKey ? Byte.valueOf(v) : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.AbstractReference2ByteFunction
 * JD-Core Version:    0.6.2
 */