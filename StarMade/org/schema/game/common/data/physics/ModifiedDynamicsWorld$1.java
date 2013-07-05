/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ final class ModifiedDynamicsWorld$1
/*     */   implements Comparator
/*     */ {
/*     */   public final int compare(TypedConstraint paramTypedConstraint1, TypedConstraint paramTypedConstraint2)
/*     */   {
/* 489 */     paramTypedConstraint2 = ModifiedDynamicsWorld.access$000(paramTypedConstraint2);
/*     */ 
/* 491 */     if (ModifiedDynamicsWorld.access$000(paramTypedConstraint1) < 
/* 491 */       paramTypedConstraint2) return -1; return 1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.ModifiedDynamicsWorld.1
 * JD-Core Version:    0.6.2
 */