/*     */ package org.apache.commons.lang3.tuple;
/*     */ 
/*     */ public class MutablePair<L, R> extends Pair<L, R>
/*     */ {
/*     */   private static final long serialVersionUID = 4954918890077093841L;
/*     */   public L left;
/*     */   public R right;
/*     */ 
/*     */   public static <L, R> MutablePair<L, R> of(L left, R right)
/*     */   {
/*  53 */     return new MutablePair(left, right);
/*     */   }
/*     */ 
/*     */   public MutablePair()
/*     */   {
/*     */   }
/*     */ 
/*     */   public MutablePair(L left, R right)
/*     */   {
/*  71 */     this.left = left;
/*  72 */     this.right = right;
/*     */   }
/*     */ 
/*     */   public L getLeft()
/*     */   {
/*  81 */     return this.left;
/*     */   }
/*     */ 
/*     */   public void setLeft(L left)
/*     */   {
/*  90 */     this.left = left;
/*     */   }
/*     */ 
/*     */   public R getRight()
/*     */   {
/*  98 */     return this.right;
/*     */   }
/*     */ 
/*     */   public void setRight(R right)
/*     */   {
/* 107 */     this.right = right;
/*     */   }
/*     */ 
/*     */   public R setValue(R value)
/*     */   {
/* 118 */     Object result = getRight();
/* 119 */     setRight(value);
/* 120 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.tuple.MutablePair
 * JD-Core Version:    0.6.2
 */