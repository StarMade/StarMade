/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ 
/*     */ final class UnionFindExt$1
/*     */   implements Comparator
/*     */ {
/*     */   public final int compare(UnionFindExt.Element paramElement1, UnionFindExt.Element paramElement2)
/*     */   {
/* 155 */     if (paramElement1.id < paramElement2.id) return -1; return 1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.UnionFindExt.1
 * JD-Core Version:    0.6.2
 */