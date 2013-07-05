/*     */ package org.apache.commons.lang3.tuple;
/*     */ 
/*     */ public final class ImmutablePair<L, R> extends Pair<L, R>
/*     */ {
/*     */   private static final long serialVersionUID = 4954918890077093841L;
/*     */   public final L left;
/*     */   public final R right;
/*     */ 
/*     */   public static <L, R> ImmutablePair<L, R> of(L left, R right)
/*     */   {
/*  58 */     return new ImmutablePair(left, right);
/*     */   }
/*     */ 
/*     */   public ImmutablePair(L left, R right)
/*     */   {
/*  69 */     this.left = left;
/*  70 */     this.right = right;
/*     */   }
/*     */ 
/*     */   public L getLeft()
/*     */   {
/*  79 */     return this.left;
/*     */   }
/*     */ 
/*     */   public R getRight()
/*     */   {
/*  87 */     return this.right;
/*     */   }
/*     */ 
/*     */   public R setValue(R value)
/*     */   {
/* 100 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.ImmutablePair
 * JD-Core Version:    0.6.2
 */