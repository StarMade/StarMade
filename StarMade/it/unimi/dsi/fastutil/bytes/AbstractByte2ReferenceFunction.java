/*     */ package it.unimi.dsi.fastutil.bytes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class AbstractByte2ReferenceFunction<V>
/*     */   implements Byte2ReferenceFunction<V>, Serializable
/*     */ {
/*     */   public static final long serialVersionUID = -4940583368468432370L;
/*     */   protected V defRetValue;
/*     */ 
/*     */   public void defaultReturnValue(V rv)
/*     */   {
/*  71 */     this.defRetValue = rv;
/*     */   }
/*     */   public V defaultReturnValue() {
/*  74 */     return this.defRetValue;
/*     */   }
/*     */   public V put(byte key, V value) {
/*  77 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public V remove(byte key) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public void clear() {
/*  83 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   public boolean containsKey(Object ok) {
/*  86 */     return containsKey(((Byte)ok).byteValue());
/*     */   }
/*     */ 
/*     */   public V get(Object ok)
/*     */   {
/*  94 */     byte k = ((Byte)ok).byteValue();
/*  95 */     return containsKey(k) ? get(k) : null;
/*     */   }
/*     */ 
/*     */   public V put(Byte ok, V ov)
/*     */   {
/* 103 */     byte k = ok.byteValue();
/* 104 */     boolean containsKey = containsKey(k);
/* 105 */     Object v = put(k, ov);
/* 106 */     return containsKey ? v : null;
/*     */   }
/*     */ 
/*     */   public V remove(Object ok)
/*     */   {
/* 114 */     byte k = ((Byte)ok).byteValue();
/* 115 */     boolean containsKey = containsKey(k);
/* 116 */     Object v = remove(k);
/* 117 */     return containsKey ? v : null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.bytes.AbstractByte2ReferenceFunction
 * JD-Core Version:    0.6.2
 */