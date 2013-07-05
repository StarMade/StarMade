/*    */ package org.schema.game.common.data.physics;
/*    */ 
/*    */ import com.bulletphysics.ContactDestroyedCallback;
/*    */ 
/*    */ class SequentialImpulseConstraintSolverExt$1 extends ContactDestroyedCallback
/*    */ {
/*    */   SequentialImpulseConstraintSolverExt$1(SequentialImpulseConstraintSolverExt paramSequentialImpulseConstraintSolverExt)
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean contactDestroyed(Object paramObject)
/*    */   {
/* 85 */     assert (paramObject != null);
/* 86 */     SequentialImpulseConstraintSolverExt.access$010(this.this$0);
/*    */ 
/* 90 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt.1
 * JD-Core Version:    0.6.2
 */