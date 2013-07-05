/*     */ package org.apache.commons.lang3.tuple;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map.Entry;
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ import org.apache.commons.lang3.builder.CompareToBuilder;
/*     */ 
/*     */ public abstract class Pair<L, R>
/*     */   implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4954918890077093841L;
/*     */ 
/*     */   public static <L, R> Pair<L, R> of(L left, R right)
/*     */   {
/*  60 */     return new ImmutablePair(left, right);
/*     */   }
/*     */ 
/*     */   public abstract L getLeft();
/*     */ 
/*     */   public abstract R getRight();
/*     */ 
/*     */   public final L getKey()
/*     */   {
/*  91 */     return getLeft();
/*     */   }
/*     */ 
/*     */   public R getValue()
/*     */   {
/* 103 */     return getRight();
/*     */   }
/*     */ 
/*     */   public int compareTo(Pair<L, R> other)
/*     */   {
/* 115 */     return new CompareToBuilder().append(getLeft(), other.getLeft()).append(getRight(), other.getRight()).toComparison();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 127 */     if (obj == this) {
/* 128 */       return true;
/*     */     }
/* 130 */     if ((obj instanceof Map.Entry)) {
/* 131 */       Map.Entry other = (Map.Entry)obj;
/* 132 */       return (ObjectUtils.equals(getKey(), other.getKey())) && (ObjectUtils.equals(getValue(), other.getValue()));
/*     */     }
/*     */ 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 147 */     return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 158 */     return '(' + getLeft() + ',' + getRight() + ')';
/*     */   }
/*     */ 
/*     */   public String toString(String format)
/*     */   {
/* 173 */     return String.format(format, new Object[] { getLeft(), getRight() });
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.Pair
 * JD-Core Version:    0.6.2
 */